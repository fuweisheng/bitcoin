<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../common/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- logo -->
<link rel="shortcut icon" href="../images/logo.ico" type="image/x-icon" />

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

<title>验证页面</title>

<style type="text/css">
#id-ul {
	font-family: Geneva, Arial, Helvetica, sans-serif;
	font-size: 14px;
	color: #5D5D5D;
}

.row {
	background-color: #EFEFEF;
	padding-left: 200px;
}
</style>
</head>

<body>

	<jsp:include page="../common/head.jsp" />
	<div class="row">

		<div class="col-xs-1"></div>
		<div class=" col-xs-6" style="min-width: 500px;">
			<h3 align="left">Wallet validate</h3>
			<br>
			<ul id="id-ul">
				<li>设置个性化的用户名可以让汇款方回显到你，以确认你的身份</li>
				<br>
				<li>
					<div align="left">请将你邮件中收到的验证码输入到验证码文本框中</div>
				</li>
			</ul>
			<br>
			<form id="id_checkForm" action="/bitcoin/user/check" method="post" class="form-horizontal" role="form">

				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">用户名：</label>
					<div class="col-sm-10">
						<input name="name" id="id_name" type="text" style="width: 330px;" class="form-control"
							placeholder="用户名">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">验证码：</label>
					<div class="col-sm-10">
						<input name="captcha" id="id_captcha" type="text" style="width: 330px;" class="form-control"
							placeholder="验证码">
					</div>
				</div>
				<br/>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button id="id_check" type="button" class="btn btn-primary">账户验证</button>
					</div>
				</div>
			</form>
			<br />
			<br />
		</div>
		
	</div>
	
	
<div id="id_myModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">操作提示</h4>
      </div>
      <div class="modal-body">
        <p id="id_hint"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">

if("${tip}"!=""){
	$("#id_hint").html("${tip}请确认&hellip;");
	$("#id_myModal").modal();
}

$("#id_check").click(function (){
	if($("#id_name").val()==""){
		alert("用户名不能为空");
		return ;
	}
	else if($("#id_captcha").val()==""){
		alert("验证码不能为空");
		return ;
	}
	$("#id_checkForm").submit();
});

</script>	
	
</body>
</html>
