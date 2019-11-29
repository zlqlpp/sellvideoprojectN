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
								
									  
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">  </label>
										<div class="col-sm-9">
											 <a id="rush" href="/m/lispasswd.do">刷新列表</a>  
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">选择 </label>
										<div class="col-sm-9">
												 	<select name="count" class="form-control" id="form-field-select-1">
												 		<option value="1">1</option>
												 		<option value="3">3</option>
												 		<option value="5" selected>5</option>
												 		<option value="10">10</option>
												 		<option value="30">30</option>
												 		<option value="50">50</option>
												 		<option value="100">100</option>
												 		<option value="500">500</option>
												 	</select>
										</div>
									</div>


									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="submit" ">
												<i class="ace-icon fa fa-check bigger-110"></i>
												生成观看码
											</button>

											 
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
													<th class="hidden-480">观看次数</th>
													<th class="hidden-480">创建时间</th>
													<th class="hidden-480">状态(0没失效1失效)</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${passwdlist}" var="u">
												<tr>
													<td height="40px" >${u.code }</td>
   													<td><a href="/c/listvideos.do?ucode=${u.code }" target="_blank">直达</a></td>
 													<td  >共:${u.count_static }次|剩余:${u.count }次</td>
 													<td  >${u.crtDate }</td>
 													<td  >${u.isdeleted }</td>
 													<td>
														<div class="hidden-sm hidden-xs btn-group">
																<a class="ace-icon fa  bigger-120" href="/m/passwdmod.do?id=${u.id }&isdeleted=0">让看</a>
															 &nbsp;&nbsp;&nbsp;&nbsp;
																<a class="ace-icon fa  bigger-120" href="/m/passwdmod.do?id=${u.id }&isdeleted=1">不许看</a>
														</div>

													</td>
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
