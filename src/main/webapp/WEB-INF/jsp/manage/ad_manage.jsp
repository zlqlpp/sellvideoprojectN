<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
		<jsp:include page="/include/bootstrap.jsp"></jsp:include>
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
								<form class="form-horizontal"  id="f" action="/m/crtpasswd.do" method="post">
								
									  


									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<a href="/m/crtgg.do" target="_blank" class="btn btn-info"  >
												<i class="ace-icon fa fa-check bigger-110"></i>
												 生成广告 
											</a>

											 
										</div>
									</div>

								</form>
								
								
								<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="row">
									<div class="col-xs-12">
										<table id="simple-table" class="table  table-bordered table-hover">
											<thead>
												<tr>
													<th>观看码</th>
													<th>快速链接</th>
													<th class="hidden-480">剩余观看次数</th>
													<th class="hidden-480">创建时间</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${passwdlist}" var="v">
												<tr>
													<td height="40px" >${v.code }</td>
   													<td><a href="/c/listvideos.do?ucode=${v.code }" target="_blank">直达</a></td>
 													<td  >${v.count }</td>
 													<td  >${v.crtDate }</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div><!-- /.span -->
								</div><!-- /.row -->



								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					
								
								

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
