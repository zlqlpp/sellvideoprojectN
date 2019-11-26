package rml.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
//import rml.RedisUtil;
import rml.Sqlite3Util;
import rml.Util;
import rml.bean.User;
import rml.bean.Video;


@Controller
@RequestMapping("/m")
public class ManageController {

	
	@RequestMapping(value="/b")
	public String menu(HttpServletRequest request) {
		
		return "manage/video_down";
	}
	
	@RequestMapping(value="/mlogin")
	public String listUser(HttpServletRequest request) {
		
		return "manage/login";
	}
	
	@RequestMapping(value="/mmain")
	public String mmain(Model model,HttpServletRequest request,HttpSession session) {
		
		String uname = request.getParameter("uname");
		if(Util.ifMLogin(session)){
			return "manage/video_manage";
		}else if(null!=uname&&"1234qwer".equals(uname)){
			session.setAttribute("muser", uname);
			return "manage/video_manage";
		}else{
			return "manage/login";
		}
	}
	
	@RequestMapping(value="/regetvideolist")
	public String videolistfromod(HttpServletRequest request,HttpSession session) {
		if(!Util.ifMLogin(session)){
			return "manage/login";
		}
		
		Logger.getLogger(ManageController.class).info("刷新视频列表");
		session.setAttribute("videolist", Util.videolistformod(session));
		
		return "manage/video_main";  
	}
	
	@RequestMapping(value="/mgotopage")
	public String mgotopage(Model model,HttpServletRequest request,HttpSession session) {
		if(!Util.ifMLogin(session)){
			return "m/mlogin";
		}
		
		String page = request.getParameter("page");
		
		if("dwnvideo".equals(page)){
			return "m/dwnvideo";
		}else if("crtgg".equals(page)){
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
		 
		/* String reqeustT = request.getParameter("t");
		 if(null!=reqeustT&&t-Long.parseLong(reqeustT)<300000){*/
			 
			 model.addAttribute("videolist",Util.getVideoListFromFileAndDB(session));
			 return "m/crtggdetail";
		/* }
		 
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
		return "m/crtgg";*/
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
	

	
	@RequestMapping(value="/vidomod")
	public String vidomod(Model model,HttpServletRequest request,HttpSession session) {
		if(!Util.ifMLogin(session)){
			return "m/mlogin";
		}
		
		String id = request.getParameter("id");
		String vid = request.getParameter("vid");
		String vname = request.getParameter("vname");
		
		List vlist = null;
		Video v = null;
		if(null!=id&&!"".equals(id)) {
			vlist = Sqlite3Util.selectfromvide("'"+vid+"'");
			v = (Video) vlist.get(0);
		}else {
			v = new Video();
			v.setVid(vid);
			v.setVname(vname);
		}
		 model.addAttribute("video",v);
		 
		Logger.getLogger(ManageController.class).info("修改視頻");
		
		return "m/modvideo";  
	}
	
	@RequestMapping(value="/vidomodupdata")
	public String vidomodupdata(Model model,HttpServletRequest request,HttpSession session) {
		if(!Util.ifMLogin(session)){
			return "m/mlogin";
		}
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = request.getParameter("id");
		String vid = request.getParameter("vid");
		String vtitle = request.getParameter("vtitle");
		String vname = request.getParameter("vname");
		Logger.getLogger(ManageController.class).info("修改：id:"+id);
		Logger.getLogger(ManageController.class).info("修改:vid:"+vid);
		Logger.getLogger(ManageController.class).info("修改:vtitle:"+vtitle);
		Logger.getLogger(ManageController.class).info("修改:vname:"+vname);
		
		
		 
		Video v = new Video(); 
		v.setId(id);
		v.setVid(vid);
		v.setVtitle(vtitle);
		v.setVname(vname);
		
		if(null==id||"".equals(id)) {
			Logger.getLogger(ManageController.class).info("修改: 沒有id,,insert 入庫"  );
			Sqlite3Util.insertvideo(v);
		}else {
			Logger.getLogger(ManageController.class).info("修改:有id,,update更新數據:" );
			Sqlite3Util.updatevideo(v);
		}
		 model.addAttribute("video",v);
		 
		Logger.getLogger(ManageController.class).info("修改視頻完成");
		
		session.setAttribute("videolist", Util.videolistformod(session));
		
		return "m/mmain";  
	}
	
	@RequestMapping(value="/videodel")
	public String clean(HttpServletRequest request,HttpSession session) {
		if(!Util.ifMLogin(session)){
			return "m/mlogin";
		}
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String vname = request.getParameter("vidodel"); 
		
		Logger.getLogger(ManageController.class).info("清空视频列表");
		
		Properties prop = (Properties) session.getAttribute("prop");
		if(prop==null) {
			prop = Util.getProp(session);
		}
		File video = new File(prop.getProperty("videoPath")+vname);
		
		File[] files = video.listFiles();
		for(int i=0;i<files.length;i++){
			files[i].delete();
		}
		
		File img = new File(prop.getProperty("imgPath")+vname);
		
		files = img.listFiles();
		for(int i=0;i<files.length;i++){
			files[i].delete();
		}
		

 session.setAttribute("videolist", Util.videolistformod(session));
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
		user.setCrtDate(code.toString());
		
		int ret = Sqlite3Util.insertuser(user);
		
		if(ret!=1) {
			Logger.getLogger(ManageController.class).info("创建观看码,code:"+user.getCode()+",观看次数，count:"+user.getCount()+",入库失败");
		}else {
			Logger.getLogger(ManageController.class).info("创建观看码,code:"+user.getCode()+",观看次数，count:"+user.getCount()+",已入库");
		}
		

		
		model.addAttribute("newcode", user.getCode());
		
		return "m/crtpasswd";  
	}
	
	@RequestMapping(value="/lispasswd")
	public String lispasswd(Model model,HttpServletRequest request,HttpSession session) {
		if(!Util.ifMLogin(session)){
			return "m/mlogin";
		}
		
		Logger.getLogger(ManageController.class).info("列出所有观看码");
		
		
		List codelist = new ArrayList();
		codelist = Sqlite3Util.selectfromuser("");
		 
		Collections.reverse(codelist);
		model.addAttribute("passwdlist",codelist);
		
		return "m/crtpasswd";  
	}
	
	@RequestMapping(value="/initdb")
	public String ts(Model model,HttpServletRequest request,HttpSession session) {
 
		
		Logger.getLogger(ManageController.class).info("测试sqlite3连接");
		Sqlite3Util.initdb();                
		return "t";  
	}
	
	@RequestMapping(value="/testb")
	public String testbootstrap(Model model,HttpServletRequest request,HttpSession session) {
		Logger.getLogger(ManageController.class).info("测试bootstrap");
		  
		return "testbootstrap";  
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
			//Logger.getLogger(MusicImplements.class).info("cd "+p.getProperty("videoPath")+";ls "+id+"\\*"); 
			//Process pro = Runtime.getRuntime().exec("cd "+p.getProperty("videoPath")+";ls "+id+"*");
			Process pro = Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c","cd "+p.getProperty("videoPath")+";ls "+id+"*" }) ;
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
    	int ret = Sqlite3Util.insertvideo(v);
    	if(ret!=1) {
    		Logger.getLogger(MusicImplements.class).info("视频下载---视频下载:...写入sqlite3 失败..." ); 
    	}else {
    		Logger.getLogger(MusicImplements.class).info("视频下载---视频下载:...已写入sqlite3..." ); 
    	}
		
    	 
    }  
} 

 

/*class CleanVideo implements Runnable{  
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
} */
