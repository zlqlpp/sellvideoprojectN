package rml.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import rml.Sqlite3Util;
//import rml.RedisUtil;
import rml.Util;
import rml.bean.User;
import rml.bean.Video;


@Controller
@RequestMapping("/c")
public class ClientController {

	
	@RequestMapping(value="/index")
	public String listUser(HttpServletRequest request) {
		
		return "index";
	}
	@RequestMapping(value="/t")
	public String t(HttpServletRequest request) {
		
		return "t";
	}
	
	@RequestMapping(value="/listvideos")
	public String listvideos(Model model,HttpServletRequest request,HttpSession session) {
		
		String seecode = request.getParameter("ucode");
		Logger.getLogger(ClientController.class).info("登录-- 前台传入的观看码为："+seecode);
		
		

		List videolist = Util.getVideoListFromFileAndDB(session);
		Logger.getLogger(ClientController.class).info("登录--读视频列表， 从文件读,然后找库，完成：" );
		
		List ulist = Sqlite3Util.selectfromuser(seecode);
		if(null==ulist||ulist.size()!=1) {
			Logger.getLogger(ClientController.class).info("登录--查库有两个用户， 或没查到用户" );
		}
		session.setAttribute("user", ulist.get(0));
		
		model.addAttribute("videolist",videolist);
		return "listvideos";
	}
	
	@RequestMapping(value="/openvideo")
	public String openvideo(Model model,HttpServletRequest request,HttpSession session) {
		
		if(!Util.ifLogin(session)){ //---------------------校验登录
			return "index";
		}
		
		
		String vid = request.getParameter("vid");  //---------------------获取前台传来的视频id
		if(null==vid||"".equals(vid)){
			return "index";
		}
		
		User u = (User) session.getAttribute("user");   //--------------------- 查看用户的观看次数
		List ulist = Sqlite3Util.selectfromuser(u.getCode());
		if(null==ulist||ulist.size()!=1) {
			Logger.getLogger(ClientController.class).info("登录--查库有两个用户， 或没查到用户" );
		}
		u = (User) ulist.get(0);
		if(u.getCount()<=0){
			return "index";
		}
		
		
		
		String wb = request.getParameter("wb");   //---------------------修改用户观看次数
		if(wb.equals("vx")){
			u.setCount(u.getCount()-0.5);
		}else{
			u.setCount(u.getCount()-1);
		}
		
		 Sqlite3Util.updateuser(u);//---------------------更新用户次数
		
		List vlist = Sqlite3Util.selectfromvide("'"+vid+"'"); //---------------------找到视频
		Video v = (Video) vlist.get(0);
		
		session.setAttribute("user", u); //--------------------- 返回前台
		 model.addAttribute("vname", v.getVname());
 
		return "openvideo";
	}
	
	
}
