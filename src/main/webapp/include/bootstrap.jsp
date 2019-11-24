<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Login Page - Ace Admin</title>

		<meta name="description" content="User login page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="/template/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="/template/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="/template/assets/css/fonts.googleapis.com.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="/template/assets/css/ace.min.css" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="/template/assets/css/ace-part2.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="/template/assets/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="/template/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="/template/assets/js/html5shiv.min.js"></script>
		<script src="/template/assets/js/respond.min.js"></script>
		<![endif]-->