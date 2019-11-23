package rml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import rml.bean.Video;
import rml.controller.ClientController;


public  class Util {
	
	
		public static boolean ifLogin(HttpSession session){
			if(null!=session.getAttribute("user")){
				return true;
			}
			return false;
		}
		
		public static boolean ifMLogin(HttpSession session){
			if(null!=session.getAttribute("muser")){
				return true;
			}
			return false;
		}
	    public static List getVideoListFromFileAndDB(HttpSession session){
	    	 
	    	Properties prop = (Properties) session.getAttribute("prop");
			if(prop==null) {
				prop = getProp(session);
			}
			
			List vlist = new ArrayList();    
			
			File file = new File(prop.getProperty("videoPath"));
	        File[] fileNamesArray = file.listFiles();
	        
	        Map map = new HashMap<String,String>();
	        String vids="";
	        if(null != fileNamesArray){
	        for (int i = 0; i < fileNamesArray.length; i++) {
	            if (fileNamesArray[i].isFile()) {
	            	if(vids.equals("")) {
	            		vids = "'"+fileNamesArray[i].getName().split("\\.")[0]+"'" ;
	            	}else {
	            		vids+=",'"+fileNamesArray[i].getName().split("\\.")[0]+"'" ;
	            	}
	            }
	        }
	        }
	        return  Sqlite3Util.selectfromvide(vids);
	        
	    }
 
	    
	    public static Properties getProp(HttpSession session){
	      	 
	    	String path3 = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"config.properties"; 
	        Properties prop = new Properties();
	        try {
				prop.load(new FileInputStream(path3));
			} catch (IOException e) {
				e.printStackTrace();
			}
	        session.setAttribute("prop", prop);
	        return prop;
	        
	    }
/*	    public static List getVideoListFromTxt(HttpSession session){
	    	 
	    	Properties prop = (Properties) session.getAttribute("prop");
			if(prop==null) {
				prop = getProp(session);
			}
			
	    	Jedis jedis = RedisUtil.getJedis();
			String videolist = jedis.get("videolist");
			
			List vlist = new ArrayList();
			if(StringUtils.isNotBlank(videolist)){
			    vlist = JSON.parseObject(videolist,ArrayList.class);
				
			}

			      
			
			File file = new File(prop.getProperty("videoPath"));
	        File[] fileNamesArray = file.listFiles();
	        
	        Map map = new HashMap<String,String>();
	        if(null != fileNamesArray){
	        for (int i = 0; i < fileNamesArray.length; i++) {
	            if (fileNamesArray[i].isFile() ) {
	            	map.put(fileNamesArray[i].getName().split("\\.")[0], fileNamesArray[i].getName());
	            }
	        }
	        }
	        
	       
	        
	        Set set = map.keySet();
	        Iterator ite = set.iterator();
	        Video video = null;
	        String vname = null;
	        String vid = null;
	        List newvlist = new ArrayList();
	        while(ite.hasNext()){
	        	video = null;
	        	vid = ite.next().toString();
        		vname = map.get(vid).toString();
	        	for(int i=0;i<vlist.size();i++){
	        		video = JSON.parseObject(vlist.get(i).toString(),Video.class);
	        		if(video.getVid().equals(vid)){
	        			break;
	        		}
	        		video = null;
	        	}
	        	if(video!=null){
	        		video.setVname(vname);
	        	}else{
	        		video = new Video();
	        		video.setVid(vid);
	        		video.setVtitle(vid);
	        		video.setVname(vname);
	        	}
	        	newvlist.add(video);
	        	
	        }
	        
	        jedis.set("videolist", JSON.toJSONString(newvlist));
	        
	      RedisUtil.returnResource(jedis);
	        
	        return newvlist;
	        
	    }*/

}