<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- 新 Bootstrap4 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>


<script>
 var npd = "${newcode}";
window.onload=function(){
 if(npd!=''){
	 alert('新生成的观看码是:'+npd);
	 document.getElementById("rush").click(); 
 }

	   
}

</script>
</head>


<body>
<jsp:include page="/include/menu.jsp"></jsp:include>
  	 
<a id="rush" href="/m/lispasswd.do">刷新列表</a>   
<hr>
	 <span style="width:100px"></span>
	 <form id="f" action="/m/crtpasswd.do" method="post">
	 	<select name="count">
	 		<option value="1">1</option>
	 		<option value="3">3</option>
	 		<option value="5" selected>5</option>
	 		<option value="10">10</option>
	 		<option value="30">30</option>
	 		<option value="50">50</option>
	 		<option value="100">100</option>
	 		<option value="500">500</option>
	 	</select>
	 	<input type="submit" value="生成新的观看码"/>
	 </form> 
  	 <hr>
  	 
  	 	<table border="1" width="70%">
 		<tr>
   			<td height="40px" >观看码</td>
   			<td>快速链接</td>
 			<td  >剩余观看次数</td>
 			<td  >创建时间</td>
   		</tr>
   		<c:forEach items="${passwdlist}" var="v">
   		<tr>
   			<td height="40px" >${v.code }</td>
   			<td><a href="/c/listvideos.do?ucode=${v.code }" target="_blank">直达</a></td>
 			<td  >${v.count }</td>
 			<td  >${v.crtDate }</td>
   		</tr>
   		</c:forEach>
   </table>
   
</body>
</html>