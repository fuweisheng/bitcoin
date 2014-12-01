<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../common/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- logo -->
	<link rel="shortcut icon" href="../images/logo.ico" type="image/x-icon" />

<title>登录</title>
</head>
<script type="text/javascript">
var inputs=document.getElementsByTagName("input");
function judge(n,s){
	if(inputs.item(n).value.length<=0){
		alert(s);
	}
	if("${tip}"!=""){
		//alert("${tip}");
	}
	
}
</script>
<body>
	<jsp:include page="../common/head.jsp" />
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script
		src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<div class="row">
		<div class="col-xs-6 col-md-5 col-md-offset-1">
			<div class="row">
				<div class="col-xs-12 col-md-6 col-md-offset-1">
					<h3>Welcome Back</h3>
				</div>
				<div class="col-xs-12 col-md-12 col-md-offset-1">
					<h5>Please enter your login details below</h5>
				</div>
				<div class="col-xs-12 col-md-12 col-md-offset-1">
					<hr>
				</div>

				<form action="/bitcoin/user/login" method="post" class="form-horizontal" role="form">
					<div class="col-xs-12 col-md-12 col-md-offset-3">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">E-mail</label>
							<div class="col-sm-6">
								<input name="email" type="email" class="form-control" id="inputEmail3"
									placeholder="Email" onBlur="judge(0,'邮箱不能为空');">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
							<div class="col-sm-6">
								<input name="password" type="password" class="form-control" id="inputPassword3"
									placeholder="Password" onBlur="judge(1,'密码不能为空');">
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10"><a href="#"><img alt="VCode" src="../images/vcode.jpg" onclick="this.src='../images/vcode.jpg?'+new Date().getTime()"/></a></div>
						</div>

						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">Captcha</label>
							<div class="col-sm-6">
								<input name="captcha" type="text" class="form-control" id="inputPassword3"
									onBlur="judge(2,'验证码不能为空');">
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-xs-12 col-md-6 col-md-offset-4">
							<input type="submit" value="Open Wallet" class="btn btn-primary">
						</div>
					</div>

				</form>


			</div>
		</div>



		<div class="col-xs-6 col-md-5">
			<div class="col-xs-12 col-md-6 col-md-offset-1">
				<h3>Forgotten something?</h3>
			</div>
			<div class="col-xs-12 col-md-12 col-md-offset-1">
				<h5>Help I've locked myself our of my account</h5>
			</div>
			<div class="col-xs-12 col-md-12 col-md-offset-1">
				<hr>
			</div>

			<div class="col-xs-12 col-md-12 col-md-offset-1">
				<b>Lost identifier or Alias<br> <br></b>
			</div>

			<div class="col-xs-12 col-md-12 col-md-offset-1">
				If you have lost your wallet identifier first check the <br>


				comfirmation email received during sign up. Can't find <br> the
				email?Click the button below and we can send you a <br> new
				one. <br> <br> <br>

			</div>

			<div class="col-xs-12 col-md-12 col-md-offset-6">
				<button type="button" class="btn btn-default btn-xs">Recover
					Wallet</button>
				<a href="/bitcoin/user/register.jsp" class="btn btn-default btn-xs">注册新钱包
				</a>
			</div>


		</div>
	</div>


</body>
</html>