<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base href="<%=basePath%>"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="css/common/common.css">
<link rel="stylesheet" href="css/common/icon.css">


<script type="text/javascript" src="js/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>

<title></title>
</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-md-4">
			<ul>
				<h4><a href="javascript:void(0)" class="parent-menu">系统管理</a></h4>
				<li><a href="javascript:void(0)" class="sub-menu" ><span class="btn-icon icon-task-layout"></span>用户管理</a></li>
				<li><a href="javascript:void(0)" class="sub-menu"><span class="btn-icon icon-door-open"></span>角色管理</a></li>
				<li><a href="javascript:void(0)" class="sub-menu" ><span class="btn-icon icon-door-group"></span>权限管理</a></li>
			</ul>
			
		</div>
		<div class="col-md-8">
			内容
		</div>
		
	</div>
</div>

</body>
</html>