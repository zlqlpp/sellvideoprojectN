package rml.controller;


import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import rml.Sqlite3Util;
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
		//Collections.reverse(videolist);
		Logger.getLogger(ClientController.class).info("登录--读视频列表， 从文件读,然后找库，完成：" );
		
		List ulist = Sqlite3Util.selectfromuser(seecode);
		if(null==ulist||ulist.size()!=1) {
			Logger.getLogger(ClientController.class).info("登录--查库有两个用户， 或没查到用户" );
		}
		session.setAttribute("user", ulist.get(0));
		
		model.addAttribute("videolist",videolist);
		return "listvideosN";
	}
	
	@RequestMapping(value="/openvideo")
	public String openvideo(Model model,HttpServletRequest request,HttpSession session) {
		Logger.getLogger(ClientController.class).info("openvideo---page..." );
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
		
		if(Sqlite3Util.updateuser(u)==0){//---------------------更新用户次数
			return "index";
		}
		
		List vlist = Sqlite3Util.selectfromvide("'"+vid+"'"); //---------------------找到视频
		Video v = (Video) vlist.get(0);
		
		int ret = Sqlite3Util.updatevideoviewcount(v);
		if(ret==0){
			Logger.getLogger(ClientController.class).info("观看视频时，更新视频的观看次数时失败！" );
		}
		
		session.setAttribute("user", u); //--------------------- 返回前台
		 model.addAttribute("vname", v.getVname());
 
		return "openvideo";
	}
	
	
}
