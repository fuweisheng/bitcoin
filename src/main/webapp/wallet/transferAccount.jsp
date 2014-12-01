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
	
	<title>转账</title>

</head>
<body>
	<jsp:include page="../common/head.jsp" />

<div class="row">

<div class="col-xs-1" ></div>
<div class=" col-xs-6" style="min-width:500px;">
	<h3>Wallet Address List</h3>
  	<form id="id_searchForm" action="/bitcoin/wallet/walletlist" method="post" class="form-inline">
	<input type="hidden" name="page.con2"/>
	<input type="hidden" name="address"/>
	<input type="hidden" name="locked"/>
	<input type="hidden" name="act"/>
	<div style="background-color: rgb(237,237,237);">
		<div class="form-group">
    		<label class="sr-only" for="searchAddress">输入Address模糊查询</label>
    		<input id="id_searchAddress" name="page.con1" value="${page.con1}" type="text" class="form-control" placeholder="输入Address模糊查询">
  		</div>
	  	<span style="margin-right:10px;margin-left:10px;">Locked</span>
	  		<div class="btn-group" style="margin-right:50px;">
			<button id="id_locked" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" value="${page.con2}" ><c:out value="${page.con2}" /><span class="caret"></span></button>
	 		<ul class="dropdown-menu">
			    <li><a id="id_lockedAll" href="#">All</a></li>
			    <li class="divider"></li>
			    <li><a id="id_lockedTrue" href="#">True</a></li>
			    <li class="divider"></li>
			    <li><a id="id_lockedFalse" href="#">False</a></li>
	  		</ul></div>
	  	<button id="id_subSearch" type="button" class="btn btn-info">Search</button>
	</div>
  
	<div>
		<table class="table table-striped table-hover">
			<thead>
			  <tr>
			    <th>Address</th>
			    <th>Amount</th>
			    <th>Locked</th>
			    <td colspan="3">只能修改Locked状态，点击即更改</td>
			  </tr>
			</thead>
			<tbody>
			  <c:forEach items="${list}" var="wallet">
			  <tr>
			    <td><c:out value="${wallet.address}"/></td>
			    <td><c:out value="${wallet.amount}"/></td>
			    <td><c:out value="${wallet.locked}"/></td>
			    <td onclick="updateInfo(this)"><button type="button" class="btn btn-info btn-sm">修改</button></td>
			    <td onclick="deleteWallet(this)"><button type="button" class="btn btn-info btn-sm">删除</button></td>
			    <td onclick="showInfo(this)"><button type="button" class="btn btn-info btn-sm">转账</button></td>
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

<div class=col-xs-1></div>


<div class="col-xs-4">
	<br/>
	  <a href="/bitcoin/wallet/addwallet" class="btn btn-info btn-sm">新建钱包</a>
	<hr/>
	
	<form id="id_transferForm" class="form-horizontal" action="/bitcoin/wallet/transfer" method="post">
	  <div class="form-group">
      	<label for="id_transferAddress" class="col-sm-3 control-label">钱包地址：</label>
	    <div class="col-sm-6">
	      <input id="id_transferAddress" name="transfer.transferAddress" type="text" class="form-control" readonly="readonly">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="id_currentAmount" class="col-sm-3 control-label">地址余额：</label>
	    <div class="col-sm-6">
	      <input id="id_currentAmount" type="text" class="form-control" readonly="readonly">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="id_transferLocked" class="col-sm-3 control-label">锁定：</label>
	    <div class="col-sm-6">
	      <input id="id_transferLocked" type="text" class="form-control"  readonly="readonly">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="id_destinationAddress" class="col-sm-3 control-label">转账地址：</label>
	    <div class="col-sm-6">
	      <input id="id_destinationAddress" name="transfer.destinationAddress" type="text" class="form-control" >
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="id_name" class="col-sm-3 control-label">回显信息：</label>
	    <div class="col-sm-6">
	      <input id="id_name" type="text" class="form-control" readonly="readonly">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="id_transferAmount" class="col-sm-3 control-label">转账金额：</label>
	    <div class="col-sm-6">
	      <input id="id_transferAmount" name="transfer.transferAmount" type="text" class="form-control">
	    </div>
	  </div>	
	  <div class="form-group">
	    <div class="col-sm-offset-3 col-sm-6">
	      <button id="id_subTransfer" type="button" class="btn btn-primary">转出云币</button>
	    </div>
	  </div>
	</form>
	
</div>

</div>

<div id="id_myModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">转账提示</h4>
      </div>
      <div class="modal-body">
        <p id="id_hint">账户不能被锁定(true),请确认&hellip;</p>
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

var re = new RegExp();
re = /^0.[1-9][0-9]*$|^[1-9][0-9]*.[0-9]+$|^[1-9][0-9]*$/;

$(document).ready(function(){
	//响应转账地址回显信息
	$("#id_destinationAddress").change(function() {
		  $("#id_name").val("傅伟生");
		//ajax
	});
	
	//检查转账数目
	$("#id_transferAmount").change(function(){
		if(!re.test($("#id_transferAmount").val())){
			$("#id_hint").html("转账金额格式不对(只能是数字，并且不为0)，请确认&hellip;");
			$("#id_myModal").modal();
		}
	});
	
	//响应下拉菜单的点击事件
	$("#id_lockedAll").click(function() {
		$("#id_locked").val("全部");
		$("#id_locked").html("All<span class=\"caret\"></span>");
	});
	$("#id_lockedTrue").click(function() {
		$("#id_locked").val("true");
		$("#id_locked").html("True<span class=\"caret\"></span>");
	});
	$("#id_lockedFalse").click(function() {
		$("#id_locked").val("false");
		$("#id_locked").html("False<span class=\"caret\"></span>");
	});
	
	//修改button的显示
	if($("#id_locked").val()=="全部"){
		$("#id_locked").html("All<span class=\"caret\"></span>");
	}
	//修改Address的显示
	if($("#id_searchForm").find("input[name='page.con1']").val()=="全部"){
		$("#id_searchForm").find("input[name='page.con1']").val("");
	}
	
	// 查询提交 
	$("#id_subSearch").click(function() {
		$("#id_searchForm").find("input[name='page.con2']").val($("#id_locked").val());
		$("#id_searchForm").find("input[name='page.currentPage']").val($("#id_currentPage").html());
		$("#id_searchForm").find("input[name='page.direction']").val("0");
		$("#id_searchForm").submit();
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
	
	//交易部分
	$("#id_subTransfer").click(function(){
		if($("#id_transferLocked").val()=="true"){
			$("#id_hint").html("账户不能被锁定(true),请确认&hellip;");
			$("#id_myModal").modal();
			return ;
		}
		if($("#id_name").val()==""){
			$("#id_hint").html("转账地址有误，请确认&hellip;");
			$("#id_myModal").modal();
			return ;
		}
		if(!re.test($("#id_transferAmount").val())){
			$("#id_hint").html("转账金额格式不对(只能是数字，并且不为0)，请确认&hellip;");
			$("#id_myModal").modal();
			return ;
		}
		//交易金钱不能大于余额
		var a =parseFloat( $("#id_currentAmount").val());
		var b =parseFloat( $("#id_transferAmount").val());
		if(a<b){
			$("#id_hint").html("转账金额大于地址余额，请确认&hellip;");
			$("#id_myModal").modal();
			return ;
		}
		$("#id_transferForm").submit();
	});
	
});

//响应转账按钮
function showInfo(me){
	var array = $(me).parent().find("td");
	$("#id_transferAddress").val($(array[0]).html());
	$("#id_currentAmount").val($(array[1]).html());
	$("#id_transferLocked").val($(array[2]).html());
}

function updateInfo(me){
	var array = $(me).parent().find("td");
	$("#id_searchForm").find("input[name='page.con2']").val($("#id_locked").val());
	$("#id_searchForm").find("input[name='page.currentPage']").val($("#id_currentPage").html());
	$("#id_searchForm").find("input[name='page.direction']").val("0");
	$("#id_searchForm").find("input[name='address']").val($(array[0]).html());
	$("#id_searchForm").find("input[name='locked']").val($(array[2]).html());
	$("#id_searchForm").submit();
}

function deleteWallet(me){
	var array = $(me).parent().find("td");
	if($(array[1]).html() != 0.0){
		$("#id_hint").html("Amount不为0.0的，不能够被删除，请确认&hellip;");
		$("#id_myModal").modal();
		return ;
	}
	$("#id_searchForm").find("input[name='page.con2']").val($("#id_locked").val());
	$("#id_searchForm").find("input[name='page.currentPage']").val($("#id_currentPage").html());
	$("#id_searchForm").find("input[name='page.direction']").val("0");
	$("#id_searchForm").find("input[name='address']").val($(array[0]).html());
	$("#id_searchForm").find("input[name='act']").val("delete");
	$("#id_searchForm").submit();
}
	
</script>

</body>
</html>