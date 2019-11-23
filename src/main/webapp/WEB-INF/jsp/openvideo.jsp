<?xml version="1.0" encoding="utf-8"?>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<script type="text/javascript">
function go(v){
	document.getElementById('video').value=v;
	var form = document.getElementById('f');
	 
	form.submit();
	 
}

window.onload=function(){
 
/* 		var ua = navigator.userAgent.toLowerCase();
		if(ua.match(/MicroMessenger/i)=="micromessenger") {
			 alert('请用手机浏览器打开链接');
			 document.getElementById('msg').style='block';
	 	} else{
	 		document.getElementById('content').style='block';
	 	} */
    
	}
</script>
 
<meta http-equiv="Cache-control" content="no-cache" />
<meta name="viewport" content="width=device-width; initial-scale=1.3;  minimum-scale=1.0; maximum-scale=2.0"/>
<meta name="MobileOptimized" content="240"/>
<meta name="Iphone-content" content="320"/>
<meta name="format-detection" content="telephone=no" />
<meta name="keywords" content="查看体验报告(客服QQ)" />
<meta name="description" content="查看体验报告(客服) ,天天看" />
<title>天天看 -  手机版 - 客服QQ</title>
<style type="text/css">
* { margin: 0; padding: 0; word-wrap: break-word; }
ul li, .xl li { list-style: none; }
.xw0 { font-weight: 400; }
.xw1 { font-weight: 700; }
.xg1, .xg1 a { color: #999 !important; }
.xg1 .xi2 { color: #999 !important; }
.xg2 { color: #666; }
.xi1, .onerror { color: #F60; }
.xi2, .xi2 a, .xi3 a { color: #369 ; }
.mtn { margin-top: 5px !important; }
.mbn { margin-bottom: 5px !important; }
.mtm { margin-top: 10px !important; }
.ptn { padding-top: 5px !important; }
.pbn { padding-bottom: 5px !important; }
.pd2 { padding: 2px !important; }
.pd5 { padding: 5px !important; }
.nopd { padding: 0px !important;}
.bt{ border-top: 1px #F2F2F2 solid !important; }
.bbn{ border-bottom: 1px #efefef solid !important; }
.ban { border: 1px #F2F2F2 solid !important;}
.xs0 { font-size : 8pt;}

a, .lkcss{ color: #004299; text-decoration: none;}
a img{ text-decoration: none; border: 0px none;}
EM { font-style: normal; }
img{border: 0;}

.odd { background: #FFF; }
.even { background: #fbfbff;}

.txt, .txt_s, .px { padding: 5px 0px; border: 1px solid #C3C3C3; background: #FFF url(./static/image/mobile/input_bg.gif) repeat-x 0 0px; width: 100%; }
.txt_s { width: 30px; }


body {background: #FFF; background-image:none; line-height: 130%; font-size: 10pt; font-style: normal; font-family: Arial,Helvetica,sans-serif; }

.hd { padding: 3px; border-bottom: 3px solid #2B7ACD; }
.hd a{ color: #2B7ACD; text-decoration: none; }
.wp { padding: 2px;}

.f_c { margin-top: 5px; padding: 5px; border: 1px #C2D5E3 solid; }
.f_c p{ line-height: 24px;}
/* common border */
.box { margin-top:3px; background: #fbfbff; padding: 3px 0; line-height:18px;}
.box h2 {}
.box .on { font-weight: 700; }
.box_ex { margin-top:0px; border-top: 0px none;}
.box_ex2{ margin-top:0px; border-top:0px none; border-left: 0px none; border-right: 0px none; }

.bm .bm_h { padding: 2px; background: #2A6EB4; border-bottom:1px #02254b solid; color: #FFF; line-height: 20px; font-size: 10pt;  }
.bm .bm_h a, .bm .bm_h .lkcss { color: #FFF; text-decoration: none;}
.bm .bm_c { padding: 3px 2px; border-bottom:1px solid #F2F2F2;  }
.bm .bm_c p{ margin-bottom: 5px; }
.bm .bm_c_bg { background: #f3f3ff; }
.bm .bm_c em { color: #999;}
.bm .bm_inf { background: #FBFBFF; padding: 5px 0;}
.bm_c .bm_user {  padding: 1px 0 1px 0px; line-height: 14px;}
.warning { margin-top: 4px; padding: 3px; border: 1px #CDCDCD solid; color:#666; }
.warning a{ color: #F26C4F;}

.pg { padding: 4px 0;}
.pg strong{ padding:2px 6px; }
.pg a{ padding: 2px 6px; }
.pipe { margin: 0 1px; font-size:12px; font-weight: 300; color: #c4c4c4; }

/* footer */
.ft{ margin:2px 0 2px 2px; padding: 1px; }

.fl { margin-top: 5px; }
.tl { margin-top: 5px;}
.tl a{ text-decoration: none;}
.tl .bm .bm_c img{ vertical-align: middle;}
.moder { margin-top: 2px; padding: 2px; border: 0px none; background: #FFF;}
.flif { border: none; background: none; color: #666;}
.ttp a{ margin: 0 2px;}

/* viewthread */
.vt { margin-top: 5px;}
.vt .bm .bm_h a { display: inline; width: auto;}
.vt .bm .bm_inf{ color: #444; }
.vt .bm .bm_c { padding: 1px; border-top: 1px #e9e9ff solid; border-bottom: 1px #e9e9ff solid; background: #fbfbff; color: #666;  }
.vt .pbody { margin-top: 1px; margin-bottom: 4px;}
.vt .pbody h2{ font-weight: 300;}
.vt .pbody .mes { padding: 1px;}
.vt .bm .bm_c .attach_h { margin-top: 4px;}
.vt .bm .bm_c .attach .vm{ width: 14px; vertical-align: middle;}
.attach img { width: 16px; vertical-align: middle; }
.vt .postmessage{ padding: 3px 0;}
.inbox { padding: 3px 0;}
.viewimg { padding: 2px;}
.viewsort img { vertical-align:top; }

/* newpost */
.sort { padding: 2px; border: 1px #efefef solid; background: #FFF; }
.quote { padding: 5px; font-style: italic; }

/* profile */
.profile_bm_c { padding: 0px !important;}
.profile_table td{ padding:0 3px; border-top: 1px #efefef solid;}
.profile_table th { border-top: 2px #efefef solid;}
.jammer { font-size: 10px; color:#FFF;}
</style>
</head>
<body>
<div style="display:block" id="msg"><h1>请使用手机浏览器打开链接</h1></div>

<div class="wp" style="display:block" id="content">

<div class="pd2"><a href="#" title="客服">客服</a></div>
<div class="box"><a href="#" title="招代理">招代理</a> </div>
<div class="tz pbn"><a href="#" >用户：${user.code },剩余观看次数：${user.count }</a></div>
 
<!--//forumdisplay_list template start-->
   <div class="tl">
    	<div class="bm">
        	<div class="bm_h">天天看(客服vx:ttktiantiankan)  </div>
			
			<a href="/c/listvideos.do"><h3>返回列表</h3></a>
        	<iframe src='/video/${video }' frameborder='0' width='auto' height='auto' allowfullscreen='true'  ></iframe>
       </div>
  </div>
 
        
            <!--//forumdisplay_list template end-->

</div>
<div class="ft">
 <p style="color:blue">注意:</p>
<p style="color:blue">1.购买主题时需要 ;</p>
  	<p><a href="#" >天天看</a></p>
</div>
</body>
</html>