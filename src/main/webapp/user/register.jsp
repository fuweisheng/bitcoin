<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/base.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<!-- logo -->
<link rel="shortcut icon" href="../images/logo.ico" type="image/x-icon" />

<title>注册</title>

<style type="text/css">
.row {
	background-color: #EFEFEF;
}
</style>
</head>


<body>
	<jsp:include page="../common/head.jsp" />

	<div class="row">
		<div class="col-md-2"></div>
		<div class=" col-md-6" style="min-width: 500px;">
			<h3>Create A New Wallet.</h3>
			<br>
			<h4>Please choose an alias and password for the new wallet.</h4>
			<br>
			<form action="/bitcoin/user/register" method="post" class="form-horizontal" role="form" name="reg_form" >

				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Email:</label>
					<div class="col-sm-10">
						<input name="user.email" type="email" style="width: 400px;" 
							class="form-control" id="inputEmail3" placeholder="Email"
							onblur="islegaldate(this.name)"><span class="text_email"></span>
					</div>
				</div>

				<div style="margin-left: 100px;">(Optional)-We will email you
					a link to your new wallet.</div>
				<br>


				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">Password:</label>
					<div class="col-sm-10">
						<input name="user.password" type="password" style="width: 400px;"
							class="form-control" id="inputPassword3" placeholder="Password" onblur="islegaldate(this.name)" />
							<span class="text_pas"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">Confirm
						Password:</label>
					<div class="col-sm-10">
						<input name="upsw2" type="password" style="width: 400px;"
							class="form-control" id="inputPassword3"
							placeholder="Confirm Password" onblur="islegaldate(this.name)" /><span
							class="text_pas2">.</span>
					</div>
				</div>

				<div class="col-sm-offset-2 clo-sm-10"><a href="#"><img alt="VCode" src="../images/vcode.jpg" onclick="this.src='../images/vcode.jpg?'+new Date().getTime()"/></a></div><br/>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">Captcha:</label>
					<div class="col-sm-10">
						<input name="captcha" type="text" style="width: 400px;" class="form-control"
							id="inputPassword3" placeholder="Captcha"><span class="text_tip"></span>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-primary"
							onClick="submitForm()">Continue</button>
					</div>
				</div>
			</form>
		</div>
		<div class="col-md-4">
		<br/><br/><br/><br/><br/><br/><br/>
		<img class="img-responsive img-rounded" alt="图片7" src="../images/7.jpg" />
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
	//检验邮箱是否填写正确
	function checkmail(arg_1) {
		//alert("hello2");
		var reg = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi; //正确的邮箱的格式
		var flag;
		flag = reg.test(arg_1);
		//	alert(flag);
		return flag;
	}

	//提示，数据正确时flag为真，text是要提示的内容，
	function tip(flag, name, text) {
		if (flag) {
			$(name).html(text);
		} else {
			$(name).html(text);
		}
	}

	//arg_1是待检查的字符串，arg_2是长度下限，arg_3是长度上限
	function islegallength(arg_1, arg_2, arg_3) {
		var flag;
		if (arg_1.length >= arg_2 && arg_1.length <= arg_3)
			flag = true;
		else
			flag = false;
		//	alert(flag);
		return flag;
	}

	//有返回值，当填写的是合法数据时返回TRUE，否则返回FALSE
	function islegaldate(arg) {
		var flag;
		switch (arg) {
		case 'email':
			//alert("hello5");
			flag = checkmail($("[name='user.email']").val());
			if (flag) {
				tip(flag, ".text_email", "Correct!");
			} else {
				tip(flag, ".text_email", "Error email,please confirm!");
			}
			return flag;
			break;
		case 'upsw':
			flag = islegallength($("[name='user.password']").val(), 6, 16);
			if (flag) //数据正确时flag为真，name是div名，text是要提示的内容
			{
				tip(flag, ".text_pas", "Correct!");
			} else {
				tip(flag, ".text_pas",
						"Password is not blank, the length of 6-16!");
			}
			return flag;
			break;
		case 'upsw2':
			var same;
			flag = ($("[name='upsw2']").val() != ''); //判断是否为空
			same = ($("[name='user.password']").val() == $("[name='upsw2']").val()); //判断输入的两次密码是否相同,相同为真，不同为假
			if (flag && same) //不为空 并且 相同
			{
				tip(flag, ".text_pas2", "Correct!");
			} else {
				if (!flag) //为空 
					tip(flag, ".text_pas2",
							"Confirm password is not blank, the length of 6-16!");
				else
					// 不为空 不同
					tip(!flag, ".text_pas2", "Password must be the same!");
			}
			return flag;
			break;

		default:
		}
	}
	// 给所有的input都设置onblur="islegaldate(this.name)"
	//$(".reg input").blur(onblur="islegaldate(this.name)");

	//点击提交按钮的时候调用，判断所有的输入项的合法性，当所有的都合法时 提交表单
	function submitForm() {
		//alert("hello");
		if (!islegaldate('email')) {
			alert("Error email！");
			return ;
		}
		if (!islegaldate('upsw')) {
			alert("Error password！");
		} else if (!islegaldate('upsw2')) {
			alert("Error confirm password！");
		} else {
			document.reg_form.submit();
		}
	}
	

</script>
</body>
</html>