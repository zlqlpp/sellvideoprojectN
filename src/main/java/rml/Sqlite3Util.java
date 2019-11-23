package rml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import rml.bean.Video;

public class Sqlite3Util {
  public static void initdb() {//--------------------------初始化数据库
    Connection connection = null;
    try {
    	 Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:/root/youtubedl/video.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      statement.executeUpdate("drop table if exists user");
      statement.executeUpdate("create table user (id integer, seecode string,count integer,crt_date string)");
      //statement.executeUpdate("insert into person values(1, 'leo')");
      //statement.executeUpdate("insert into person values(2, 'yui')");
      statement.executeUpdate("drop table if exists video");
      statement.executeUpdate("create table video (id integer, vid string,vtitle string,vname string,vlenght string,vsize integer,crt_date string,vkind integer)");
      //statement.executeUpdate("insert into person values(1, 'leo')");
      //statement.executeUpdate("insert into person values(2, 'yui')");
      connection.commit();
      /*ResultSet rs = statement.executeQuery("select * from person");
      while(rs.next()) {
        System.out.println("name = " + rs.getString("name"));
        System.out.println("id = " + rs.getInt("id"));
      }*/
    }
    catch(Exception e) {
      System.err.println(e.getMessage());
    }
    finally {
      try {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e) {
        System.err.println(e.getMessage());
      }
    }
  }
  
  
  public static void insertuser(Video v) {   //----------------------下载完视频后插入表中
	    Connection connection = null;
	    try {
	    	 Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:/root/youtubedl/video.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	      
	      //statement.executeUpdate("create table video (id integer, vid string,vtitle string,vname string,vlenght string,vsize integer,crt_date string,vkind integer)");
	      int id = new Long(new Date().getTime()).intValue();
	       StringBuffer sb = new StringBuffer("insert into video values("+id+", ");
	       sb.append("'"+v.getVid()+"',");
	       sb.append("'"+v.getVtitle()+"',");
	       sb.append("'"+v.getVname()+"',");
	       sb.append("'"+v.getVlenght()+"',");
	       sb.append(""+v.getVsize()+",");
	       sb.append("'"+v.getCrt_date()+"',");
	       sb.append( v.getVkind()+")" );
	       Logger.getLogger(Sqlite3Util.class).info("sql："+ sb.toString()); 
	      int ret = statement.executeUpdate(sb.toString());
	      connection.commit();
	      Logger.getLogger(Sqlite3Util.class).info("sql-ret："+ ret); 
	       
	    }
	    catch(Exception e) {
	      System.err.println(e.getMessage());
	    }
	    finally {
	      try {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e) {
	        System.err.println(e.getMessage());
	      }
	    }
	  }
  
  public static List selectfromvide(String vids) {//--------------------------
	    Connection connection = null;
	    try {
	    	 Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:/root/youtubedl/video.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      String sql = "select * from video where vid in ("+vids+")";
	      Logger.getLogger(Sqlite3Util.class).info("sql："+ sql); 
	       ResultSet rs = statement.executeQuery(sql);
	       List vlist = new ArrayList();
	       Video v = null;
	      while(rs.next()) {
	    	  v = new Video();
	    	  v.setId(rs.getInt("id"));
	    	  v.setVid(rs.getString("vid"));
	    	  v.setVtitle(rs.getString("vtitle"));
	    	  v.setVname(rs.getString("vname"));
	    	  v.setVlenght(rs.getString("vlenght"));
	    	  v.setVsize(rs.getInt("vsize")+"");
	    	  v.setCrt_date(rs.getString("crt_date"));
	    	  v.setVkind(rs.getString("vkind"));
	         vlist.add(v);
	      } 
	      Logger.getLogger(Sqlite3Util.class).info("sql--select ret count："+ vlist.size()); 
	      return vlist;
	    } catch(Exception e) {
	      System.err.println(e.getMessage());
	    } finally {
	      try {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e) {
	        System.err.println(e.getMessage());
	      }
	    }
	    return null;
	  }
}