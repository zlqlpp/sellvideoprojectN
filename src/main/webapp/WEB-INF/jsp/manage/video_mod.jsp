<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
		<jsp:include page="/include/bootstrap.jsp"></jsp:include>
		<script type="text/javascript">
function go(v){
	document.getElementById('video').value=v;
	var form = document.getElementById('f');
	 
	form.submit();
	 
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
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> t_id </label>
										<div class="col-sm-9">
											<input type="text" value="${video.id }"  disable="disable"  class="col-xs-10 col-sm-5" />
											<input type="hidden" value="${video.id }"  id="id" name="id"  class="col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 视频ID </label>
										<div class="col-sm-9">
											<input type="text" value="${video.vid }"  disable="disable" class="col-xs-10 col-sm-5" />
											<input type="hidden" value="${video.vid }"  id="vid" name="vid" class="col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 视频name </label>
										<div class="col-sm-9">
											<input type="text" value="${video.vname }" disable="disable" class="col-xs-10 col-sm-5" />
											<input type="hidden" value="${video.vname }"  id="vname" name="vname"  class="col-xs-10 col-sm-5" />
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> 视频标题 </label>
										<div class="col-sm-9">
											<input type="text" id="vtitle" name="vtitle" value="${video.vtitle }"  class="form-control" />
										</div>
									</div>


									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="submit">
												<i class="ace-icon fa fa-check bigger-110"></i>
												Submit
											</button>

											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset">
												<i class="ace-icon fa fa-undo bigger-110"></i>
												Reset
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
