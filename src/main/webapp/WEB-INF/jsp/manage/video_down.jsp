<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
		<jsp:include page="/include/bootstrap.jsp"></jsp:include>
 <script>
 
function down(){
	$.ajax({
	    type : 'POST',
	    url :'down.do',
	    data :{"url":$('#url').val()},
	    dataType : 'JSON',
	    success : function(dto) {
	    	alert('已加入下载任务列表，正在下载中');
	    	document.getElementById("url").value="";;
	    }});
}
 
 
</script>
	</head>

	<body class="no-skin">
	

		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

<jsp:include page="/include/main_left.jsp"></jsp:include>
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>

							<li>
								<a href="#">Forms</a>
							</li>
							<li class="active">Form Elements</li>
						</ul>

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- /.nav-search -->
					</div>

					<div class="page-content">

						<div class="page-header">
							<h1>
								Form Elements
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Common form elements and layouts
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<form class="form-horizontal"  action="/m/vidomodupdata.do" method="post">
								
									  

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> 视频url|视频地址 </label>
										<div class="col-sm-9">
											<input type="text" id="url"    class="form-control" />
										</div>
									</div>


									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="button" onclick="down();">
												<i class="ace-icon fa fa-check bigger-110"></i>
												下载
											</button>

											 
										</div>
									</div>

								</form>

												</div>

											</div>
										</div>

									</div>
								


							</div><!-- /.col -->
						</div><!-- /.row -->

			<jsp:include page="/include/main_footer.jsp"></jsp:include>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>




	</body>
</html>
