<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>骑手注册</title>
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
				<h1>${hint}</h1>
			</div>
			<form role="form" action="RiderRegister" method="post">
				<div class="form-group">
					 <label for="exampleInputName1">骑手名</label><input type="text" class="form-control" id="exampleInputName1" name="rider_name" />
				</div>
				<div class="form-group">
					 <label for="exampleInputPassword1">密码</label><input type="password" class="form-control" id="exampleInputPassword1" name="rider_pwd" />
				</div>
				<div class="form-group">
					 <label for="exampleInputPassword2">确认密码</label><input type="password" class="form-control" id="exampleInputPassword2" name="rider_pwd_check" />
				</div>

				
				<div class="checkbox">
					 <label><input type="checkbox" />Check me out</label>
				</div> <button type="submit" class="btn btn-default" >提交</button>
			</form>
			<div class="page-header">
				<h1>
					Example page header <small>Subtext for header</small>
				</h1>
			</div>
		</div>
		<div class="col-md-3 column">
		</div>
	</div>
	
	
</body>
</html>