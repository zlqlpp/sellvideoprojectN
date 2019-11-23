package rml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	      
	      statement.executeUpdate("create table video (id integer, vid string,vtitle string,vname string,vlenght string,vsize integer,crt_date string,vkind integer)");
	      
	       StringBuffer sb = new StringBuffer("insert into video values("+UUID.randomUUID()+", ");
	       sb.append("'"+v.getVid()+"',");
	       sb.append("'"+v.getVtitle()+"',");
	       sb.append("'"+v.getVname()+"',");
	       sb.append("'"+v.getVlenght()+"',");
	       sb.append(""+v.getVsize()+",");
	       sb.append("'"+v.getCrt_date()+"',");
	       sb.append( v.getVkind()+")" );
	       
	      statement.executeUpdate(sb.toString());
	       
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

 
	      
	       ResultSet rs = statement.executeQuery("select * from vieo where vid in ("+vids+")");
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