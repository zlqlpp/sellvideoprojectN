package rml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import rml.bean.User;
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
      statement.executeUpdate("create table user (id integer PRIMARY KEY, seecode string,count double,crt_date long)");

      statement.executeUpdate("drop table if exists video");
      statement.executeUpdate("create table video (id integer PRIMARY KEY, vid string,vtitle string,vname string,vlenght string,vsize integer,crt_date long,vkind integer)");

    }
    catch(Exception e) {
    	 e.printStackTrace();
    }
    finally {
      try {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e) {
       e.printStackTrace();
      }
    }
  }
  
  
  public static int insertvideo(Video v) {   //----------------------下载完视频后插入表中
	    Connection connection = null;
	    try {
	    	 Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:/root/youtubedl/video.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	      
	      //statement.executeUpdate("create table video (id integer, vid string,vtitle string,vname string,vlenght string,vsize integer,crt_date string,vkind integer)");
	      int id = new Long(new Date().getTime()).intValue();
	       StringBuffer sb = new StringBuffer("insert into video (vid ,vtitle ,vname ,vlenght ,vsize ,crt_date ,vkind ) values( ");
	       sb.append("'"+v.getVid()+"',");
	       String title = null!=v.getVtitle()?v.getVtitle().trim():"";
	       sb.append("'"+title+"',");
	       sb.append("'"+v.getVname()+"',");
	       sb.append("'"+v.getVlenght()+"',");
	       sb.append(""+v.getVsize()+",");
	       sb.append(""+new Date().getTime()+",");
	       sb.append( v.getVkind()+")" );
	       Logger.getLogger(Sqlite3Util.class).info("sql："+ sb.toString()); 
	      int ret = statement.executeUpdate(sb.toString());
	      
	      Logger.getLogger(Sqlite3Util.class).info("sql-ret："+ ret); 
	       return ret;
	    }
	    catch(Exception e) {
	    	 e.printStackTrace();
	    }
	    finally {
	      try {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e) {
	    	  e.printStackTrace();
	      }
	    }
	    return 0;
	  }
  
  public static List selectfromvide(String vids) {//----------------------查询表中的所有视频
	    Connection connection = null;
	    try {
	    	 Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:/root/youtubedl/video.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      String sql = "select * from video where vid in ("+vids+") order by id desc"; 
	      if(StringUtils.isBlank(vids)){
	    	  sql = "select * from video  order by crt_date desc"; 
	      }
	      Logger.getLogger(Sqlite3Util.class).info("sql："+ sql); 
	       ResultSet rs = statement.executeQuery(sql);
	       List vlist = new ArrayList();
	       Video v = null;
	      while(rs.next()) {
	    	  v = new Video();
	    	  v.setId(rs.getInt("id")+"");
	    	  v.setVid(rs.getString("vid"));
	    	  v.setVtitle(rs.getString("vtitle"));
	    	  v.setVname(rs.getString("vname"));
	    	  v.setVlenght(rs.getString("vlenght"));
	    	  v.setVsize(rs.getInt("vsize")+"");
	    	  v.setCrt_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(rs.getLong("crt_date"))));
	    	  v.setVkind(rs.getString("vkind"));
	         vlist.add(v);
	      } 
	      Logger.getLogger(Sqlite3Util.class).info("sql--select ret count："+ vlist.size()); 
	      return vlist;
	    } catch(Exception e) {
	    	 e.printStackTrace();
	    } finally {
	      try {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e) {
	    	  e.printStackTrace();
	      }
	    }
	    return null;
	  }
  
  public static int insertuser(User u) {   //----------------------创建观看码实体
	    Connection connection = null;
	    try {
	    	 Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:/root/youtubedl/video.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	      
	      //statement.executeUpdate("create table user (id integer, seecode string,count integer,crt_date string)");
	      
	      //int id = new Long(new Date().getTime()).intValue();
	       StringBuffer sb = new StringBuffer("insert into user (seecode ,count ,crt_date ) values(");
	       sb.append("'"+u.getCode()+"',");
	       sb.append(u.getCount()+",");
	       sb.append(""+u.getCrtDate()+")");
	       
	       Logger.getLogger(Sqlite3Util.class).info("sql："+ sb.toString()); 
	      int ret = statement.executeUpdate(sb.toString());
	      
	      Logger.getLogger(Sqlite3Util.class).info("sql-ret："+ ret); 
	       return ret;
	    }
	    catch(Exception e) {
	    	 e.printStackTrace();
	    }
	    finally {
	      try {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e) {
	    	  e.printStackTrace();
	      }
	    }
	    return 0;
	  }
  
  public static List selectfromuser(String seecode) {//----------------------查询表中的所有视频
	    Connection connection = null;
	    try {
	    	 Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:/root/youtubedl/video.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      String sql = "select * from user  ";
	      if(null!=seecode&&!"".equals(seecode)) {
	    	  sql+=" where seecode='"+seecode+"'";
	      }
	      sql+=" order by id desc ";
	      Logger.getLogger(Sqlite3Util.class).info("sql："+ sql); 
	       ResultSet rs = statement.executeQuery(sql);
	       List ulist = new ArrayList();
	       User u = null;
	      while(rs.next()) {
	    	  u = new User();
	    	  u.setId(rs.getInt("id")+"");
	    	  u.setCode(rs.getString("seecode"));
	    	  u.setCount(rs.getDouble("count"));
	    	  u.setCrtDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(rs.getLong("crt_date"))));
	    	   
	         ulist.add(u);
	      } 
	      Logger.getLogger(Sqlite3Util.class).info("sql--select ret count："+ ulist.size()); 
	      return ulist;
	    } catch(Exception e) {
	    	 e.printStackTrace();
	    } finally {
	      try {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e) {
	    	  e.printStackTrace();
	      }
	    }
	    return null;
	  }
  public static int updateuser(User u) {   //----------------------创建观看码实体
	    Connection connection = null;
	    try {
	    	 Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:/root/youtubedl/video.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	      
	       StringBuffer sb = new StringBuffer("update user set count="+u.getCount()+" where id="+u.getId()+""); 
 
	       Logger.getLogger(Sqlite3Util.class).info("sql："+ sb.toString()); 
	      int ret = statement.executeUpdate(sb.toString());
	      
	      Logger.getLogger(Sqlite3Util.class).info("sql-ret："+ ret); 
	      return ret;
	    } catch(Exception e) {
	    	 e.printStackTrace();
	    } finally {
	      try {
	        if(connection != null)
	          connection.close();
	      } catch(SQLException e) {
	    	  e.printStackTrace();
	      }
	    }
	    return 0;
	  }
  
  public static int updatevideo(Video v) {   //----------------------创建观看码实体
	    Connection connection = null;
	    try {
	    	 Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:/root/youtubedl/video.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	      
	       StringBuffer sb = new StringBuffer("update video set vtitle='"+v.getVtitle()+"' where id="+v.getId()+""); 

	       Logger.getLogger(Sqlite3Util.class).info("sql："+ sb.toString()); 
	      int ret = statement.executeUpdate(sb.toString());
	      
	      Logger.getLogger(Sqlite3Util.class).info("sql-ret："+ ret); 
	      return ret;
	    } catch(Exception e) {
	    	 e.printStackTrace();
	    } finally {
	      try {
	        if(connection != null)
	          connection.close();
	      } catch(SQLException e) {
	    	  e.printStackTrace();
	      }
	    }
	    return 0;
	  }
}