<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>SSM基础框架</title>

<meta name="description" content="User login page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${basePath}/assets/css/bootstrap.css" />
<link rel="stylesheet" href="${basePath}/assets/css/font-awesome.css" />
<!-- text fonts -->
<link rel="stylesheet" href="${basePath}/assets/css/ace-fonts.css" />
<!-- ace styles -->
<link rel="stylesheet" href="${basePath}/assets/css/ace.css" />
<link rel="stylesheet" href="${basePath}/assets/css/ace-rtl.css" />
</head>

<body class="login-layout light-login">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class=""></i>
							</h1>
						</div>
						<div class="space-6"></div>
						<div class="position-relative" style="margin-top: 50%;">
							<div id="login-box" class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="ace-icon fa fa-coffee green"></i>
											欢迎使用XXX管理系统
										</h4>
										<div class="space-6"></div>
										<form>
											<fieldset>
												<label class="block clearfix">
													<span class="block input-icon input-icon-right">
														<input type="text" class="form-control" placeholder="用户名" name="username" id="username" />
														<i class="ace-icon fa fa-user"></i>
													</span>
												</label>
												<label class="block clearfix">
													<span class="block input-icon input-icon-right">
														<input type="password" class="form-control" placeholder="密码" name="password" id="password" />
														<i class="ace-icon fa fa-lock"></i>
													</span>
												</label>
												<div class="space"></div>

												<div class="clearfix">
													<button type="button" id="login-btn" class="width-35 pull-left btn btn-sm btn-primary">
														<i class="ace-icon fa fa-key"></i>
														<span class="bigger-110">登录</span>
													</button>
													<button type="reset" class="width-35 pull-right btn btn-sm">
														<i class="ace-icon fa fa-refresh"></i>
														<span class="bigger-110">重置</span>
													</button>
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>
									</div>
									<!-- /.widget-main -->
									<div class="toolbar clearfix">
										<div>
											<a href="#" data-target="#forgot-box" class="forgot-password-link"> </a>
										</div>
										<div>
											<a href="#" data-target="#signup-box" class="user-signup-link"> </a>
										</div>
									</div>
								</div>
								<!-- /.widget-body -->
							</div>
							<!-- /.login-box -->
						</div>
						<!-- /.position-relative -->
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery || document.write("<script src='${basePath}/assets/js/jquery.js'>" + "<"+"/script>");
	</script>
	<!-- <![endif]-->
	<script type="text/javascript">
		if('ontouchstart' in document.documentElement)
	        document.write("<script src='${basePath}/assets/js/jquery.mobile.custom.js'>" + "<"+"/script>");
	</script>
	<!-- layer -->
	<script src="${basePath}/js/layer/layer.js"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($) {
	        //回车事件
	        $('#username').keypress(function(e) {
		        if(e.which == 13) {
			        $('#password').focus();
		        }
	        });
	        $('#password').keypress(function(e) {
		        if(e.which == 13) {
			        jQuery("#login-btn").click();
		        }
	        });
	        $('#login-btn').click(function(event) {
		        event.stopPropagation();
		        var $btn = $(this);
		        if($btn.hasClass("disabled")) {
			        return false;
		        }
		        var $loginname = $('#username');
		        var $password = $('#password');
		        if(!$loginname.val()) {
			        layer.msg('请输入用户名！', {
			            icon : 0,
			            time : 1000 });
			        $loginname.focus();
			        return false;
		        }
		        if(!$password.val()) {
			        layer.msg('请输入密码！', {
			            icon : 0,
			            time : 1000 });
			        $password.focus();
			        return false;
		        }
		        var submitData = {
		            username : $loginname.val(),
		            password : $password.val(),
		            url : "${url}" };
		        $btn.addClass("disabled");
		        $.post("doLogin", submitData, function(data) {
			        $btn.removeClass("disabled");
			        console.log(data);
			        if(data.code == 0) {
				        layer.msg("登录成功", {
				            icon : 1,
				            time : 1000 }, function() {
					        window.top.location.href = "index";
				        });
			        } else {
				        layer.msg(data.msg, {
				            icon : 0,
				            time : 1000 });
			        }
		        }, "json");
		        return false;
	        });
        });
	</script>
</body>
</html>


