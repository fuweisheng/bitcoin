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
	
	<title>交易记录</title>
</head>
<body>
	<jsp:include page="../common/head.jsp" />
	
<div class="container" style="min-width:500px;padding-right:200px;">
	<h2>My Transactions List</h2>
	<h4>我的交易列表</h4>
	<form id="id_pageForm" action="/bitcoin/wallet/transaction" method="post">
		<input type="hidden" name="page.con1"/>
		<input type="hidden" name="page.con2"/>
	  <div style="background-color: rgb(237,237,237);">
		<span style="margin-right:10px;margin-left:10px;">类型</span>
			<div class="btn-group" style="margin-right:30px;">
			<button id="id_type" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" value="${page.con1}"><c:out value="${page.con1}"/> <span class="caret"></span></button>
	 		<ul class="dropdown-menu">
			    <li><a id="id_typeAll" href="#">全部</a></li>
			    <li class="divider"></li>
			    <li><a id="id_typeIn" href="#">汇入</a></li>
			    <li class="divider"></li>
			    <li><a id="id_typeOut" href="#">汇出</a></li>
	  		</ul></div>
	  	<span style="margin-right:10px;">状态</span>
	  		<div class="btn-group" style="margin-right:50px;">
			<button id="id_state" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" value="${page.con2}"><c:out value="${page.con2}"/><span class="caret"></span></button>
	 		<ul class="dropdown-menu">
			    <li><a id="id_stateAll" href="#">全部</a></li>
			    <li class="divider"></li>
			    <li><a id="id_stateFinish" href="#">完成</a></li>
			    <li class="divider"></li>
			    <li><a id="id_stateWait" href="#">等待</a></li>
			    <li class="divider"></li>
			    <li><a id="id_stateFail" href="#">失败</a></li>
	  		</ul></div>
	  	<button id="id_subPageForm" type="button" class="btn btn-info">Search</button>
	  </div>
	
	<div>
		<table class="table table-striped table-hover">
			<thead>
			  <tr>
			    <th>汇入/汇出地址</th>
			    <th>类型</th>
			    <th>数量</th>
			    <th>状态</th>
			    <th>时间</th>
			  </tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="transfer" >
			  <tr>
			    <td><c:out value="${transfer.destinationAddress}"/></td>
			    <td><c:out value="${transfer.transferType}"/></td>
			    <td><c:out value="${transfer.transferAmount}"/></td>
			    <td><c:out value="${transfer.transferState}"/></td>
			    <td><c:out value="${transfer.transferDate}"/></td>
			  </tr>
			 </c:forEach>
			</tbody>
		</table>
	</div>
	
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





	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	//响应类型下拉菜单的点击事件
	$("#id_typeAll").click(function() {
		$("#id_type").val("全部");
		$("#id_type").html("全部<span class=\"caret\"></span>");
	});
	$("#id_typeIn").click(function() {
		$("#id_type").val("汇入");
		$("#id_type").html("汇入<span class=\"caret\"></span>");
	});
	$("#id_typeOut").click(function() {
		$("#id_type").val("汇出");
		$("#id_type").html("汇出<span class=\"caret\"></span>");
	});
	
	//响应状态下拉菜单的点击事件
	$("#id_stateAll").click(function() {
		$("#id_state").val("全部");
		$("#id_state").html("全部<span class=\"caret\"></span>");
	});
	$("#id_stateFinish").click(function() {
		$("#id_state").val("完成");
		$("#id_state").html("完成<span class=\"caret\"></span>");
	});
	$("#id_stateWait").click(function() {
		$("#id_state").val("等待");
		$("#id_state").html("等待<span class=\"caret\"></span>");
	});
	$("#id_stateFail").click(function() {
		$("#id_state").val("失败");
		$("#id_state").html("失败<span class=\"caret\"></span>");
	});
	
	//提交表单
	$("#id_subPageForm").click(function(){
		$("#id_pageForm").find("input[name='page.con1']").val($("#id_type").val());
		$("#id_pageForm").find("input[name='page.con2']").val($("#id_state").val());
		$("#id_pageForm").find("input[name='page.currentPage']").val("1");
		$("#id_pageForm").find("input[name='page.direction']").val("0");
		$("#id_pageForm").submit();
	});
	
	//page表单提交
	$("#id_previousPage").click(function(){
		$("#id_pageForm").find("input[name='page.con1']").val($("#id_type").val());
		$("#id_pageForm").find("input[name='page.con2']").val($("#id_state").val());
		$("#id_pageForm").find("input[name='page.currentPage']").val($("#id_currentPage").html());
		$("#id_pageForm").find("input[name='page.direction']").val("-1");
		$("#id_pageForm").submit();
	});
	$("#id_nextPage").click(function(){
		$("#id_pageForm").find("input[name='page.con1']").val($("#id_type").val());
		$("#id_pageForm").find("input[name='page.con2']").val($("#id_state").val());
		$("#id_pageForm").find("input[name='page.currentPage']").val($("#id_currentPage").html());
		$("#id_pageForm").find("input[name='page.direction']").val("1");
		$("#id_pageForm").submit();
	});
});
</script>

</body>
</html>