<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<link rel="stylesheet" type="text/css" href="/css/css.css">
<script type="text/javascript">
function go(v){
	document.getElementById('vid').value=v;
	var form = document.getElementById('f');
	 
	form.submit();
	 
}

window.onload=function(){
	//document.getElementById('content').style='block';
		var ua = navigator.userAgent.toLowerCase();
		if(ua.match(/MicroMessenger/i)=="micromessenger") {
			 //alert('请用手机浏览器打开链接');
			 //document.getElementById('msg').style='block';
			 document.getElementById('wb').value='vx';
	 	} else{
	 		document.getElementById('wb').value='web';
	 	}
    //alert(document.getElementById('wb').value);
	}
</script>
</head>
<body>
<form action="/c/openvideo.do" method="post"  id="f">
  	 	<input type="hidden" id="vid" name="vid" />
  	 	<input type="hidden" id="wb" name="wb" />
</form>
  	 
	<div style="padding: 0px 3px 3px;">
		<div id="gdt_area" style="margin-left: 3px;">
			<div style="font-size: 16px;">
				<div style="background: #fff">
					<div>
						<table width="100%" height="100%"
							style="background-color: #333333;">
							<tbody>
								<tr>
									<td style="text-align: right;">
										<ul class="nav nav-second-level"><li class="ca_one" data-curr="1"  data-ca_type="1"><a>国产</a></li></ul>
<ul class="nav nav-second-level"><li class="ca_one" data-curr="1"  data-ca_type="1"><a>国产</a></li></ul>
<ul class="nav nav-second-level"><li class="ca_one" data-curr="1"  data-ca_type="1"><a>国产</a></li></ul>
<ul class="nav nav-second-level"><li class="ca_one" data-curr="1"  data-ca_type="1"><a>国产</a></li></ul>
<ul class="nav nav-second-level"><li class="ca_one" data-curr="1"  data-ca_type="1"><a>国产</a></li></ul>
<ul class="nav nav-second-level"><li class="ca_one" data-curr="1"  data-ca_type="1"><a>国产</a></li></ul>
<ul class="nav nav-second-level"><li class="ca_one" data-curr="1"  data-ca_type="1"><a>国产</a></li></ul>
<ul class="nav nav-second-level"><li class="ca_one" data-curr="1"  data-ca_type="1"><a>国产</a></li></ul>
<ul class="nav nav-second-level"><li class="ca_one" data-curr="1"  data-ca_type="1"><a>国产</a></li></ul>
<ul class="nav nav-second-level"><li class="ca_one" data-curr="1"  data-ca_type="1"><a>国产</a></li></ul>

										 
									</td>
								</tr>
							</tbody>
						</table>
						
						
						<div style="clear: both"></div>
						<div class="box">
							<ul class="list" id="article_list">
							<c:forEach items="${videolist}" var="v">
								<li>
										<div class="list_img">
											<img data-savepage-src="" src="/videoimg/${v.vid }.jpg" onerror="" alt="">
										</div>
										<div class="list_info">
											<div >
												<font color="Red"><p style="font-size: 11px;"> 已有2053人付款<b>5</b>元观看 </p></font>
												<div onclick="go('${v.vid}')">${v.vtitle }---(时长:${v.vlenght })</div>
											</div>
										</div>
								</li>
							</c:forEach>
	
							</ul>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>