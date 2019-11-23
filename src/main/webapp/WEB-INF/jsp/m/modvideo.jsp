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


<script type="text/javascript">
function go(v){
	document.getElementById('video').value=v;
	var form = document.getElementById('f');
	 
	form.submit();
	 
}
</script>
</head>
<body>
  	<h3>功能列表</h3>
 
  	 <a href="/m/mgotopage.do?page=dwnvideo"  class="btn btn-primary">下载视频</a>
  	 <a href="/m/regetvideolist.do"   		  class="btn btn-success">刷新前后台视频列表</a>
  	 <a href="/m/lispasswd.do" class="btn btn-info">观看码管理</a>
  	 <a href="/m/mgotopage.do?page=crtgg" 	  class="btn btn-warning">生成宣传页</a>
  	 <a href="/m/mgotopage.do?page=clsvideo"  class="btn btn-danger">清空视频</a>
  	 <br><br>
  	 <hr/>
  	 
  	 
  	 <form action="/m/vidomodupdata.do">
  	 	表id(不用管這列):<input type="text" value="${video.id }" disabled="disabled" id="id" name="id"/>
  	 	視頻ID：<input type="text" value="${video.vid }" disabled="disabled" id="vid" name="vid"/>
  	 	視頻標題：<textare type="text" value="${video.vname }"  id="vname" name="vname"></textare>
  	 </form>
  	 
	<table border="1" width="70%">
		 <tr>
   			<td    >表id(不用管這列) </td>
   			<td   >视频ID</td>
   			<td    >视频标题+时长</td>
   			<td    >編輯</td>
   			<td    >刪除</td>
   		</tr>
   		
   		<c:forEach items="${videolist}" var="v">
   		<tr>
   			<td height="40px" >${v.id } </td>
   			<td height="40px" >${v.vid } </td>
   			<td height="40px" >${v.vtitle }-${v.vlenght }</td>
		    <td height="40px" weigth="40px"  ><a href="/m/vidomod.do?id=${v.id }&vid=${v.vid }">編輯</a></td>
 			<td height="40px" weigth="40px"  ><a href="#">刪除</a></td>
   		</tr>
   		</c:forEach>
   </table>
   
</body>
</html>