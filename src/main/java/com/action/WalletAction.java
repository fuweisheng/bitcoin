package com.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;

import com.common.Page;
import com.entity.TransferRecord;
import com.opensymphony.xwork2.ActionSupport;
import com.service.TransferRecordService;
import com.service.WalletService;

@Controller
@ParentPackage("struts-interceptor")
@Namespace(value = "/wallet")
@InterceptorRefs({ 
	@InterceptorRef(value = "myInterceptor")
})
@Results({ @Result(name = "validate", location = "/user/check.jsp"),
		@Result(name = "login", location = "/user/login.jsp")
})
public class WalletAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List list;
	private Page page;
	private String locked;
	private String address;
	private String act;
	private TransferRecord transfer;
	private static final Logger logger = Logger.getLogger(WalletAction.class);
	@Autowired
	private TransferRecordService transferRecordService;
	@Autowired
	private WalletService walletService;

	// 处理transactionRecord.jsp页面
	@Action(value = "transaction", results = { @Result(name = "success", location = "/wallet/transactionRecord.jsp") })
	public String transaction() throws Exception {
		if (page == null) {
			page = new Page();
		}
		int userId = (Integer) ServletActionContext.getContext().getSession().get("id");
		list = this.transferRecordService.findList(page, userId);
		return SUCCESS;
	}

	// 处理transferAmount.jsp页面的右边页面
	@Action(value = "transfer", results = {
			@Result(name = "success", type="redirect",location = "/wallet/transaction"),
			@Result(name="fail",location="/wallet/walletlist")
			})
	public String transfer() throws Exception {
		if(this.transfer==null){
			return "fail";
		}
		int userId = (Integer) ServletActionContext.getContext().getSession().get("id");
		this.transferRecordService.addTransfer(this.transfer,userId);
		return SUCCESS;
	}

	// 处理transferAmount.jsp的左边部分
	@Action(value = "walletlist", results = { @Result(name = "success", location = "/wallet/transferAccount.jsp") })
	public String walletlist() throws Exception {
		if (page == null) {
			page = new Page();
		}
		if("delete".equals(this.act)){
			this.walletService.deleteWallet(address);
		}
		else if (this.address != null && this.locked != null)
			this.walletService.updateLocked(this.locked, this.address);
		int userId = (Integer) ServletActionContext.getContext().getSession().get("id");
		list = this.walletService.findList(page, userId);
		return SUCCESS;
	}

	// 钱包页面的Action
	@Action(value = "walletpage", results = { @Result(name = "success", location = "/wallet/walletPage.jsp") })
	public String walletpage() throws Exception {
		if (page == null) {
			page = new Page();
		}
		int userId = (Integer) ServletActionContext.getContext().getSession().get("id");
		list = this.walletService.findList(page, userId);
		return SUCCESS;
	}

	// 增加钱包
	@Action(value = "addwallet", results = { @Result(name = "success", type = "redirect", location = "/wallet/walletpage") })
	public String addwallet() throws Exception {
		int userId = (Integer) ServletActionContext.getContext().getSession().get("id");
		this.walletService.addWallet(userId);
		return SUCCESS;
	}
	
	// 增加钱包
	@Action(value = "getname")
	public void getname() throws Exception {
		logger.info(this.address);
		String name = this.walletService.getName(this.address);
		logger.info(name);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=UTF-8");
		response.getWriter().write(name);
//		return null;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public TransferRecord getTransfer() {
		return transfer;
	}

	public void setTransfer(TransferRecord transfer) {
		this.transfer = transfer;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

}
