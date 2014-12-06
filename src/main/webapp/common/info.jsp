<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../common/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
	<!-- logo -->
	<link rel="shortcut icon" href="../images/logo.ico" type="image/x-icon" />
	
	
	<title>信息提示</title>
</head>
<body style="background-image: url(../images/bg.jpg)">
	<jsp:include page="../common/head.jsp" />
<div class="container-fluid" >
	<div id="id_error" class="alert alert-info" style="display: none; height:150px;padding-top:50px;padding-left:100px;">
		<div>ERROR!</div>
		<div>WARNING:注册失败，你输入的账号已被占用！<a href="../user/register.jsp">点击返回</a></div>
	</div>

	<div id="id_success" class="alert alert-info" style="display: none; height:150px;padding-top:50px;padding-left:100px;" >
		<div>SUCCESS!</div>
		<div>注册成功：您必须在五天之内到你的邮箱验证，否则账号将失效！<a href="../user/login.jsp">点击登录</a></div>
	</div>
</div>



	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(document).ready(function (){
	if( "${tip}" == "error"){
		$("#id_error").css("display","block");
	}else if("${tip}" == "success"){
		$("#id_success").css("display","block");
	}
	
});

</script>
</body>
</html>