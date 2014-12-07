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
	
	
	<title>欢迎访问</title>
<style type="text/css">
.banner { position: relative; overflow: auto; }
.banner li { list-style: none; }
.banner ul li { float: left; }
img {
	width: 100%;
	height:500px;
}

#id_pre {
position: absolute;
top :50%;
left:30px;
z-index: 2;
}
#id_next {
position: absolute;
top:50%;
right:10px;
z-index: 2;
}

</style>
</head>
<body>
	<div class="navbar navbar-inverse" ><p class="navbar-text navbar-left"><span style="font-size: 18px;">YUN - Cloud Coin</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/bitcoin/user/login.jsp" class="navbar-link">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/bitcoin/wallet/walletpage" class="navbar-link">Wallet</a>&nbsp;&nbsp;&nbsp;&nbsp;Stats&nbsp;&nbsp;&nbsp;&nbsp;API&nbsp;&nbsp;&nbsp;&nbsp;矿机</p>
	<p class="navbar-text navbar-right" style="margin-right: 20px;">Signed in as <span id="id_emailName"></span> <a href="/bitcoin/user/logout" class="navbar-link">logout</a></p></div>

	<div class="banner row" style="margin-top:-20px; ">
		<span id="id_pre"><a href="#" class="unslider-arrow prev"><img alt="l" style="width:22px;height:33px;" src="../images/l.png"></a></span>
		<span id="id_next"><a href="#" class="unslider-arrow next"><img alt="r" style="width:22px;height:33px;" src="../images/r.png"></a></span>
		<ul class="col-md-12">
			<li><img alt="" src="../images/1.jpg"></li>
			<li><img alt="" src="../images/2.jpg"></li>
			<li><img alt="" src="../images/3.jpg"></li>
		</ul>
	</div>
	

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed-->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	 
	<script src="//code.jquery.com/jquery-latest.min.js"></script>
	<script src="//unslider.com/unslider.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$('.banner').unslider({
			speed: 500,               //  The speed to animate each slide (in milliseconds)
			delay: 3000,              //  The delay between slide animations (in milliseconds)
			complete: function() {},  //  A function that gets called after every slide animation
			keys: true,               //  Enable keyboard (left, right) arrow shortcuts
			dots: true,               //  Display dot navigation
			fluid: true              //  Support responsive design. May break non-responsive designs
		});
	    
	    var unslider = $('.banner').unslider();

	    $('.unslider-arrow').click(function() {
	        var fn = this.className.split(' ')[1];
			
	        //  Either do unslider.data('unslider').next() or .prev() depending on the className
	        unslider.data('unslider')[fn]();
	    });
	});
		
	</script>
</body>
</html>