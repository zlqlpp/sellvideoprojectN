package rml.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import rml.RedisUtil;
import rml.Sqlite3Util;
import rml.Util;
import rml.bean.User;
import rml.bean.Video;


@Controller
@RequestMapping("/m")
public class ManageController {

	
	
	@RequestMapping(value="/mlogin")
	public String listUser(HttpServletRequest request) {
		
		return "m/mlogin";
	}
	
	@RequestMapping(value="/mmain")
	public String listvideos(Model model,HttpServletRequest request,HttpSession session) {
		
		String code = request.getParameter("passwd");
		if(Util.ifMLogin(session)){
			return "m/mmain";
		}else if(null!=code&&"1234qwer".equals(code)){
			session.setAttribute("muser", code);
			return "m/mmain";
		}else{
			return "m/mlogin";
		}
	}
	
	@RequestMapping(value="/mgotopage")
	public String mgotopage(Model model,HttpServletRequest request,HttpSession session) {
		if(!Util.ifMLogin(session)){
			return "m/mlogin";
		}
		
		String page = request.getParameter("page");
		
		if("dwnvideo".equals(page)){
			return "m/dwnvideo";
		}/*else if("crtpasswd".equals(page)){
			return "m/crtpasswd";
		}*/else if("crtgg".equals(page)){
			return "m/crtgg";
		} else if("clsvideo".equals(page)){
			return "m/clsvideo";
		} 
		
		return "m/mmain";
	}
	
	@RequestMapping(value="/crtgg")
	public String crtgg(Model model,HttpServletRequest request,HttpSession session) {

		
		 Long t = new Date().getTime();
		 Logger.getLogger(ManageController.class).info("新的的crtgg："+t);
		 
		 String reqeustT = request.getParameter("t");
		 if(null!=reqeustT&&t-Long.parseLong(reqeustT)<300000){
			 
			 model.addAttribute("videolist",Util.getVideoListFromFileAndDB(session));
			 return "m/crtggdetail";
		 }
		 
			if(!Util.ifMLogin(session)){
				return "m/mlogin";
			}
		 
		 Long crtgg = (Long) session.getAttribute("crtgg");
		 Logger.getLogger(ManageController.class).info("session中的crtgg："+crtgg);
		 
		 if(null==crtgg||(t-crtgg)>300000) {
			 crtgg=t;
			 Logger.getLogger(ManageController.class).info("新的crtgg："+crtgg);
		 } 
		 session.setAttribute("crtgg", crtgg);
		 Logger.getLogger(ManageController.class).info("url："+request.getRequestURL());
		 Logger.getLogger(ManageController.class).info("uri："+request.getRequestURI());
		 model.addAttribute("t",request.getRequestURL()+"?t="+crtgg);
		return "m/crtgg";
	}

	
	@ResponseBody
	@RequestMapping(value="/down",method = RequestMethod.POST)
	public Map<String,Object> down(HttpServletRequest request,HttpSession session) {
		
		Map<String,Object> retMap = new HashMap<String,Object>();
		String url = request.getParameter("url");
		Logger.getLogger(ManageController.class).info("新下载视频的URL："+url);
		Properties prop = (Properties) session.getAttribute("prop");
		if(prop==null) {
			prop = Util.getProp(session);
		}
		Thread thread = new Thread(new MusicImplements(url,prop));
		thread.start();
		
        retMap.put("stat", "suc");
		return retMap;
	}
	
	@RequestMapping(value="/regetvideolist")
	public String regetvideolist(HttpServletRequest request,HttpSession session) {
		if(!Util.ifMLogin(session)){
			return "m/mlogin";
		}
		
		Logger.getLogger(ManageController.class).info("刷新视频列表");
		
		session.setAttribute("videolist", Util.getVideoListFromFileAndDB(session));
		
		return "m/mmain";  
	}
	
	@RequestMapping(value="/clean")
	public String clean(HttpServletRequest request,HttpSession session) {
		if(!Util.ifMLogin(session)){
			return "m/mlogin";
		}
		
		Logger.getLogger(ManageController.class).info("清空视频列表");
		Properties prop = (Properties) session.getAttribute("prop");
		if(prop==null) {
			prop = Util.getProp(session);
		}
		File file = new File(prop.getProperty("videoPath"));
		
		File[] files = file.listFiles();
		for(int i=0;i<files.length;i++){
			files[i].delete();
		}
		
		Thread thread = new Thread(new CleanVideo(prop));
		thread.start();
		
		session.setAttribute("videolist", new ArrayList());
		return "m/mmain";  
	}
	
	@RequestMapping(value="/crtpasswd")
	public String crtpasswd(Model model,HttpServletRequest request,HttpSession session) {
		if(!Util.ifMLogin(session)){
			return "m/mlogin";
		}
		
		Logger.getLogger(ManageController.class).info("创建观看码");
		
		String count = request.getParameter("count");
		Date date = new Date();
		Long code = date.getTime();
		User user = new User();
		user.setCode(code.toString());
		user.setCount(Double.parseDouble(count));
		user.setCrtDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		
		Jedis jedis = RedisUtil.getJedis();
		
		
		
		String str = jedis.get("codemap");
		Map map = new HashMap();
		if(StringUtils.isNotBlank(str)){
		    map = JSON.parseObject(str,HashMap.class);
		}
		map.put(user.getCode(), user);
		jedis.set("codemap", JSON.toJSONString(map));
		
		
		str = jedis.get("codelist");
		List codelist = new ArrayList();
		if(StringUtils.isNotBlank(str)){
		    codelist = JSON.parseObject(str,ArrayList.class);
		}
			codelist.add(user);
		jedis.set("codelist", JSON.toJSONString(codelist));
		
		
		Logger.getLogger(ManageController.class).info("创建观看码,code:"+user.getCode()+",观看次数，count:"+user.getCount()+",并存放redis,一份map,一份list");

		RedisUtil.returnResource(jedis);
		
		model.addAttribute("newcode", user.getCode());
		
		return "m/crtpasswd";  
	}
	
	@RequestMapping(value="/lispasswd")
	public String lispasswd(Model model,HttpServletRequest request,HttpSession session) {
		if(!Util.ifMLogin(session)){
			return "m/mlogin";
		}
		
		Logger.getLogger(ManageController.class).info("列出所有观看码");
		//readCodes(session);
		
		Jedis jedis = RedisUtil.getJedis();
		String str = jedis.get("codelist");
		
		List codelist = new ArrayList();
		if(StringUtils.isNotBlank(str)){
		    codelist = JSON.parseObject(str,ArrayList.class);
		}
		RedisUtil.returnResource(jedis);
		Collections.reverse(codelist);
		model.addAttribute("passwdlist",codelist);
		
		return "m/crtpasswd";  
	}
	
	@RequestMapping(value="/ts")
	public String ts(Model model,HttpServletRequest request,HttpSession session) {
 
		
		Logger.getLogger(ManageController.class).info("测试sqlite3连接");
		Sqlite3Util.initdb();                
		return "t";  
	}
	
}


//--------------------------------------------------
class MusicImplements implements Runnable{  
	private String durl = "";
	private Properties p;
	public MusicImplements(String url,Properties p) {
		this.durl = url;
		this.p = p;
	}
	
    public void run() {  
    	
        List<String> processList = new ArrayList<String>();
		try {
			Process pro = Runtime.getRuntime().exec("youtube-dl --get-id "+durl);
			pro.waitFor();
			BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				processList.add(line);
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String id = processList.get(0).toString();
		Logger.getLogger(MusicImplements.class).info("视频下载---视频id："+id );
 
		
          processList = new ArrayList<String>();
		try {
			Process pro = Runtime.getRuntime().exec("youtube-dl --get-title "+durl);
			pro.waitFor();
			BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				processList.add(line);
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String title = processList.get(0).toString();
		Logger.getLogger(MusicImplements.class).info("视频下载---视频名字："+ title);  
		
		



        processList = new ArrayList<String>();
		try {
			Process pro = Runtime.getRuntime().exec("youtube-dl --get-duration "+durl);
			pro.waitFor();
			BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				processList.add(line);
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String length = processList.get(0).toString();
		Logger.getLogger(MusicImplements.class).info("视频下载---视频时长："+ length); 
		
		
		Logger.getLogger(MusicImplements.class).info("视频下载---视频下载:...进行中..." );
        try {
        	//Process pro = Runtime.getRuntime().exec("youtube-dl -o "+p.getProperty("videoPath")+"-%(title)s.%(ext)s "+durl);
        	Process pro = Runtime.getRuntime().exec("youtube-dl -o  "+p.getProperty("videoPath")+"%(id)s.%(ext)s "+durl);
        	pro.waitFor();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        Logger.getLogger(MusicImplements.class).info("视频下载---视频下载:...完成..." );
        
        processList = new ArrayList<String>();
		try {
			Process pro = Runtime.getRuntime().exec("ls "+p.getProperty("videoPath")+id+"*");
			pro.waitFor();
			BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				processList.add(line);
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String fullname = processList.get(0).toString();
		Logger.getLogger(MusicImplements.class).info("视频下载---下载后的文件名："+ fullname); 
		
		
        try {
        	//Process pro = Runtime.getRuntime().exec("youtube-dl -o "+p.getProperty("videoPath")+"-%(title)s.%(ext)s "+durl);
        	Process pro = Runtime.getRuntime().exec("youtube-dl --write-thumbnail --skip-download -o "+p.getProperty("imgPath")+"%(id)s.%(ext)s "+durl);
        	pro.waitFor();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        Logger.getLogger(MusicImplements.class).info("视频下载---视频下载:...完成..." );
        
/*    	try {
         	Process pro = Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c","echo '"+name+"--------"+id+"--------"+longth+"' >>"+ p.getProperty("videoNamePath")}) ;
         	pro.waitFor();
         } catch ( Exception e) {
             e.printStackTrace();
         }*/
        Video v = new Video();
    	v.setVtitle(title);
    	v.setVid(id);
    	v.setVname(fullname);
    	v.setVlenght(length);
    	
    	/*Jedis jedis = RedisUtil.getJedis();
		String videolist = jedis.get("videolist");
		
		List vlist = new ArrayList();
		if(StringUtils.isNotBlank(videolist)){
		    vlist = JSON.parseObject(videolist,ArrayList.class);
			
		}
		vlist.add(v);
		jedis.set("videolist",JSON.toJSONString(vlist));
		      
		RedisUtil.returnResource(jedis);*/
		Logger.getLogger(MusicImplements.class).info("视频下载---视频下载:...已写入sqlite3..." ); 
    	 
    }  
} 

 

class CleanVideo implements Runnable{  
	private Properties p;
	public CleanVideo(Properties p) {
		this.p = p;
	}
	
    public void run() {  
    	try {
         	Process pro = Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c"," >"+ p.getProperty("videoNamePath")}) ;
         	pro.waitFor();
         } catch ( Exception e) {
             e.printStackTrace();
         }
          
    }  
} 
