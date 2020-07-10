<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>用户登录</title>
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-theme.css"/>
	<script src="jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="jumbotron">
					<h1>
						Hello, world!
					</h1>
					<p>
						This is a template for a simple marketing or informational website. It includes a large callout called the hero unit and three supporting pieces of content. Use it as a starting point to create something more unique.
					</p>
					<p>
						 <a class="btn btn-primary btn-large" href="#">Learn more</a>
					</p>
				</div>
				<nav class="navbar navbar-default" role="navigation">
					<div class="navbar-header">
						 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">Brand</a>
					</div>
					
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="active">
								 <a href="#">Link</a>
							</li>
							<li>
								 <a href="#">Link</a>
							</li>
							<li class="dropdown">
								 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li>
										 <a href="#">Action</a>
									</li>
									<li>
										 <a href="#">Another action</a>
									</li>
									<li>
										 <a href="#">Something else here</a>
									</li>
									<li class="divider">
									</li>
									<li>
										 <a href="#">Separated link</a>
									</li>
									<li class="divider">
									</li>
									<li>
										 <a href="#">One more separated link</a>
									</li>
								</ul>
							</li>
						</ul>
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input type="text" class="form-control" />
							</div> <button type="submit" class="btn btn-default">Submit</button>
						</form>
						<ul class="nav navbar-nav navbar-right">
							<li>
								 <a href="#">Link</a>
							</li>
							<li class="dropdown">
								 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li>
										 <a href="#">Action</a>
									</li>
									<li>
										 <a href="#">Another action</a>
									</li>
									<li>
										 <a href="#">Something else here</a>
									</li>
									<li class="divider">
									</li>
									<li>
										 <a href="#">Separated link</a>
									</li>
								</ul>
							</li>
						</ul>
					</div>
					
				</nav>
				<!-- ------------------------------------页头------------------------ -->
				
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
								你好 用户!
							</h4> <strong>不是用户? 请选择登录身份!</strong>若无账号 请 <a href="user_register.jsp" class="alert-link">注册</a>
						</div>
						<div class="row clearfix">
							<div class="col-md-2 column">
							</div>
							<div class="col-md-10 column">
								<div class="btn-group">
									 <button class="btn btn-default">用户</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
									<ul class="dropdown-menu">
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
										<li>
											 <a href="admin_login.jsp">管理员</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<form action="Login" method="post" class="form-horizontal" role="form">
							<div class="form-group">
								 <label for="inputEmail3" class="col-sm-2 control-label">登录名</label>
								<div class="col-sm-10">
									<input class="form-control" id="inputEmail3" name="name" required="required" />
								</div>
							</div>
							<div class="form-group">
								 <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="inputPassword3" name="pwd" required="required"/>
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
				
				
				<!-- ------------------------------------页尾------------------------ -->
				<div class="page-header">
					<h1>
						Example page header <small>Subtext for header</small>
					</h1>
				</div>
				<div class="row clearfix">
					<div class="col-md-6 column">
						<p>
							 <em>Git</em> 是一个分布式的版本控制系统，最初由 <strong>Linus Torvalds</strong> 编写，用作Linux内核代码的管理。在推出后，Git在其它项目中也取得了很大成功，尤其是在 <small>Ruby</small> 社区中。
						</p>
					</div>
					<div class="col-md-6 column">
						 <address> <strong>Twitter, Inc.</strong><br /> 795 Folsom Ave, Suite 600<br /> San Francisco, CA 94107<br /> <abbr title="Phone">P:</abbr> (123) 456-7890</address>
					</div>
				</div>
			</div>
		</div>
	</div>
		
</body>
</html>