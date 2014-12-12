<%@ page language="java" pageEncoding="UTF-8"%>

<div class="navbar navbar-inverse" ><p class="navbar-text navbar-left"><span style="font-size: 18px;">YUN - Cloud Coin</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/bitcoin/user/login.jsp" class="navbar-link">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/bitcoin/wallet/walletpage" class="navbar-link">Wallet</a>&nbsp;&nbsp;&nbsp;&nbsp;Stats&nbsp;&nbsp;&nbsp;&nbsp;API&nbsp;&nbsp;&nbsp;&nbsp;矿机</p>
<p class="navbar-text navbar-right" style="margin-right: 20px;">Signed in as <span id="id_emailName"></span> <a href="/bitcoin/user/logout" class="navbar-link">logout</a></p></div>
<div  style="margin-top: -20px;background-color: rgb(247,247,247); height:110px;min-width: 500px;" >
  <div class="col-md-6" style="padding-left:150px;">
	<p style="margin-top: 20px;font-size: 25px;"><span>My Wallet</span>&nbsp;&nbsp;<span style="color:rgb(157,157,157);">Be Your On Bank.</span></p>
	<p >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/bitcoin/wallet/walletpage">Wallet Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/bitcoin/wallet/transaction">My Transaction</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/bitcoin/wallet/walletlist">transfer</a></p>
  </div>
  <div class="col-md-6" style="padding-left:50px;padding-top:25px;"><p id="id_priceInfo"></p></div>
</div>
<script type="text/javascript">

window.setTimeout("getPriceInfo()", 100);

if("${sessionScope.email}"!=""){
	document.getElementById("id_emailName").innerHTML="${email}";
}
function getPriceInfo(){
	var xmlhttp;
	if (window.XMLHttpRequest){
		 // code for IE7+, Firefox, Chrome, Opera, Safari
	 	 xmlhttp=new XMLHttpRequest();
	}else{
		// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function(){
		if (xmlhttp.readyState==4 && xmlhttp.status==200){
			document.getElementById("id_priceInfo").innerHTML=xmlhttp.responseText;
		}
	}
	xmlhttp.open("get","/bitcoin/user/getBitcoinInfo?t="+new Date().getTime(),true);
	xmlhttp.send();
}
</script>
