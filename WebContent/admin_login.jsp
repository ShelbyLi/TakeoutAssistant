<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>管理员登录</title>
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-theme.css"/>
	<script src="jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
				<div class="row clearfix">
					<div class="col-md-3 column">
					</div>
					<div class="col-md-6 column">
						<div class="page-header">
							<h1>
								Example page header <small>Subtext for header</small>
							</h1>
						</div>
						<div class="alert alert-success alert-dismissable">
							 <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
							<h4>
								你好 管理员! 
							</h4> <strong>不是管理员? 请选择登录身份!</strong>
							<%-- <p><strong>${hint }</strong></p> --%>
						</div>
						<div class="alert alert-success alert-dismissable">
							 <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
							<%-- <strong>${hint }</strong>&nbsp;&nbsp;&nbsp;&nbsp;若无账号 请 <a href="shop_register.jsp" class="alert-link">注册</a> --%>
							<strong>&nbsp;&nbsp;&nbsp;&nbsp;请管理员不要轻易泄露密码!</strong>
						</div>
						<div class="row clearfix">
							<div class="col-md-2 column">
							</div>
							<div class="col-md-10 column">
								<div class="btn-group">
									 <button class="btn btn-default">管理员</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
									<ul class="dropdown-menu">
										<li>
											 <a href="user_login.jsp">用户</a>
										</li>
										<li>
											 <a href="shop_login.jsp">商家</a>
										</li>
										<li>
											 <a href="rider_login.jsp">骑手</a>
										</li>
										<!-- <li class="disabled">
											 <a href="#">另一操作</a>
										</li> -->
										<li class="divider">
										</li>
										<!-- <li>
											 <a href="admin_login.jsp">管理员</a>
										</li> -->
									</ul>
								</div>
							</div>
						</div>
						<form action="AdminLogin" method="post" class="form-horizontal" role="form">
							<div class="form-group">
								 <label for="inputEmail3" class="col-sm-2 control-label">登录名</label>
								<div class="col-sm-10">
									<input class="form-control" id="inputEmail3" name="admin_name" required="required" />
								</div>
							</div>
							<div class="form-group">
								 <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="inputPassword3" name="admin_pwd" required="required"/>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<div class="checkbox">
										 <label><input type="checkbox" />Remember me</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									 <button type="submit" class="btn btn-default">登录</button>
								</div>
							</div>
						</form>
					</div>
					<div class="col-md-3 column">
					</div>
				</div>
</body>
</html>