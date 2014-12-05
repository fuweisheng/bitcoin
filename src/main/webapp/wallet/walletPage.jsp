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

<title>我的钱包</title>
</head>
<body>
	<jsp:include page="../common/head.jsp" />
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->

	<div class="row">
		<div class="col-md-8">
			<div style="width: 600px; margin-left: 150px;">

				<h2>Wallet Address List</h2>
			</div>
			<div class="col-md-10 col-md-offset-1">
			<h3>我的钱包地址列表</h3>
			<form id="id_searchForm" action="/bitcoin/wallet/walletpage" method="post">
			<table id="main" class="table table-striped">
				<thead>
					<tr>
					<th>Address</th>
					<th>amout</th>
					<th>locket</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${list}" var="wallet" >
				  <tr>
				    <td><c:out value="${wallet.address}"/></td>
				    <td><c:out value="${wallet.amount}"/></td>
				    <td><c:out value="${wallet.locked}"/></td>
				  </tr>
				 </c:forEach>
				</tbody>
			</table>
	
		   <div>
		   <input type="hidden" name="page.currentPage"/>
		   <input type="hidden" name="page.direction"/>
			<ul class="pager">
	    	  <li><a id="id_previousPage" href="#">上一页</a></li>
	    	  <li>当前第<span id="id_currentPage"><c:out value="${page.currentPage}"/></span>页</li>
	    	  <li>总共<span><c:out value="${page.totalPage}"/></span>页</li>
	    	  <li><a id="id_nextPage" href="#">下一页</a></li>
	  		</ul>
	  	   </div>
	  	 </form>
		</div>
		</div>
		<div class="col-md-4">
			<br>
			
			<a href="/bitcoin/wallet/addwallet" class="btn btn-default btn-sm active">新建钱包</a>
			
			<a href="/bitcoin/wallet/walletlist" class="btn btn-default btn-sm active">付款</a>
			<br />
			<br />
			<br />
			<br />
			<img class="img-thumbnail img-responsive" alt="图片5" src="../images/5.jpg" />
		</div>

	</div>
	

	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script
		src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

	<script language="JavaScript">
		$(document).ready(function() {
			$("#btnAdd").click(function() {
				var whichEl = $("#firstRow");
				if (whichEl.css("display") == "none") {
					whichEl.show();
					$(this).val("取消新增");
				} else {
					whichEl.hide();
					$(this).val("新增");
				}
			});

			$("#submitCng").click(function() {
				if (window.confirm('确定要提交吗？')) {
					//提交代码
					window.location.reload();
				}
			});
			
			//page表单提交
			$("#id_previousPage").click(function(){
				$("#id_searchForm").find("input[name='page.con2']").val($("#id_locked").val());
				$("#id_searchForm").find("input[name='page.currentPage']").val($("#id_currentPage").html());
				$("#id_searchForm").find("input[name='page.direction']").val("-1");
				$("#id_searchForm").submit();
			});
			$("#id_nextPage").click(function(){
				$("#id_searchForm").find("input[name='page.con2']").val($("#id_locked").val());
				$("#id_searchForm").find("input[name='page.currentPage']").val($("#id_currentPage").html());
				$("#id_searchForm").find("input[name='page.direction']").val("1");
				$("#id_searchForm").submit();
			});
		});
	</script>
</body>
</html>