<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>videoList</title>
<!-- 新 Bootstrap4 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>



</head>
<body>
<jsp:include page="/include/menu.jsp"></jsp:include>
  	 
  	 
  	 <form action="/m/vidomodupdata.do" method="post">
  	 	表id(不用管這列):<input type="text" value="${video.id }"  id="id" name="id"/>
  	 	視頻ID：<input type="text" value="${video.vid }"  id="vid" name="vid"/>
  	 	視頻name:<input type="text" value="${video.vname }"  id="vname" name="vname"/>
  	 	視頻標題：<textarea   id="vtitle" name="vtitle">${video.vtitle }</textarea>
  	 	<input type="submit" value="提交">
  	 </form>
  	 
 
   
</body>
</html>