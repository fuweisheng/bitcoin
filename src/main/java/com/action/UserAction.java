package com.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.entity.Price;
import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.PriceService;
import com.service.UserService;
import com.utils.RandomValidateCode;

@Controller
@ParentPackage("struts-default")
@Namespace(value = "/user")
public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserAction.class);

	private User user; // 用于注册的页面
	private String captcha; // 用于登录和注册的页面
	private String email; // 用于登录的页面
	private String password; // 用于登录的页面
	private String name; // 用于验证的页面
	private String tip; // 用于页面的提示

	@Autowired
	private UserService userService;
	@Autowired
	private PriceService priceService;

	// 注册的Action
	@Action(value = "register", results = {
			@Result(name = "success", location = "/common/info.jsp"),
			@Result(name = "errVCode", location = "/user/register.jsp") })
	public String register() throws Exception {

		if (this.user == null) {
			this.setTip("error");
			return "success";
		}
		String vcode = (String) ServletActionContext.getContext().getSession()
				.get(RandomValidateCode.RANDOMCODEKEY);
		if (!vcode.equalsIgnoreCase(this.captcha)) {
			this.setTip("验证码错误!");
			return "errVCode";
		}
		if(this.userService.addUser(this.user)){
			this.setTip("success");
			return SUCCESS;
		}else{
			this.setTip("error");
			return SUCCESS;
		}
		
	}

	// 验证的Action
	@Action(value = "check", results = {
			@Result(name = "success",type="redirect",location = "/wallet/walletpage"),
			@Result(name = "fail", location = "/user/check.jsp") })
	public String check() throws Exception {
		logger.info(this.name);
		logger.info(this.captcha);
		if (this.userService.check(this.name, this.captcha)) {
			this.setTip("成功!");
			return SUCCESS;
		}
		this.setTip("验证码有误!");
		return "fail";
	}

	// 登录的Action
	@Action(value = "login", results = {
			@Result(name = "success", type = "redirect", location = "/wallet/walletpage"),
			@Result(name = "fail", location = "/user/login.jsp") })
	public String login() throws Exception {
		String vcode = (String) ServletActionContext.getContext().getSession()
				.get(RandomValidateCode.RANDOMCODEKEY);
		// 测试数据传输用的
		// logger.info(this.captcha);
		// logger.info(vcode);
		// logger.info(this.email);
		// logger.info(this.password);
		if (!vcode.equalsIgnoreCase(this.captcha)) {
			this.setTip("验证码错误!");
			return "fail";
		}
		if (this.userService.login(this.email, this.password)) {
			return SUCCESS;
		}
		this.setTip("账号或密码错误!");
		return "fail";
	}
	
	// 退出的Action
	@Action(value = "logout", results = {@Result(name = "success", type = "redirect", location = "/common/index.jsp")})
	public String logout() throws Exception {
		this.userService.logout();
		return SUCCESS;
	}
	
	//进入读取bitcoin价格
	@Action(value = "getBitcoinInfo")
	public void getBitcoinInfo() throws Exception {
		
		Price price = this.priceService.getPrice();
		StringBuffer buf= new StringBuffer();
		buf.append("比特币价格    ")
			.append("最高价格：")
			.append(price.getMaxprice())
			.append("   ")
			.append("最低价格：")
			.append(price.getMinprice())
			.append("   ")
			.append("当前价格")
			.append(price.getPrice())
			.append("   ");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=UTF-8");
		response.getWriter().write(buf.toString());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

}
