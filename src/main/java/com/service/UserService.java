package com.service;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entity.User;
import com.entity.Validate;
import com.utils.MailUtils;
import com.utils.Md5Utils;
import com.utils.VCodeUtils;

@Transactional
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//增加用户的事务
	//同时要将邮箱验证码存入Validate中id是自动生成的
	public boolean addUser(User user){
		
		if(this.isExistedEmail(user.getEmail())){
			return false;
		}
		
		Validate validate = new Validate();
		String vcode = VCodeUtils.Vcode(6);
		validate.setVcode(vcode);
		validate.setState(0);
		validate.setRegisterDate(new Date());
		//发送邮件
		this.sendEmail(user, vcode);
		//validate关联User
		validate.setUser(user);
		//提取密码加密
		String pwd = user.getPassword();
		user.setPassword(Md5Utils.md5(pwd));
		user.setValidate(validate);
		this.userDao.save(user);
		
		return true;
	}
	
	public boolean isExistedEmail(String email){
		List<User> users = this.userDao.getUsers();
		if(users == null)
			return false;
	
		for(User u : users){
			if(email.equalsIgnoreCase(u.getEmail()))
				return true;
		}
		return false;
	}
	
	public void sendEmail(User user,String vcode){
		
		
		MailUtils mailUtils = new MailUtils();
		mailUtils.setFromMail("傅伟生");
		mailUtils.setUsername("juanornot@126.com");
		mailUtils.setPassword("6635660ws,");
		mailUtils.setSmtpHost("smtp.126.com");
		mailUtils.setVurl("/bitcoin/user/login.jsp");
		String subject = "云币钱包注册验证";
		StringBuffer text = new StringBuffer("");
		text.append("<p><h2>你的云币账户已准备就绪！</h2></p>");
		text.append("<p>请点击以下链接进行激活</p>");
		text.append("<p><a href=\"" + mailUtils.getVurl() + "\">"
				+ mailUtils.getVurl() + "</a></p>");
		text.append("<P>或输入：" + vcode + "</P>");
		text.append("<P>到注册表格中。</P>");
		mailUtils.sendMailUtis(user.getEmail(), subject, text.toString());
		
	}
	
	//删除用户的事务
	//级联删除相关的Validate实体，
	public void deleteUser(User user){
		
		//这只是简单的实现
		this.userDao.delete(user);
	}
	
	//获取用户，根据id
	public User getUser(int id){
		return this.userDao.get(id);
	}

	//获取id,根据email
	public int getId(String email){
		return this.userDao.getId(email);
	}
	
	//登录，登录成功后，将email,state，id放入Session中以便后面的拦截器使用
	public boolean login(String email,String password){
		List<User> list=this.userDao.loadAll();
		if(list == null)
			return false;
		password = Md5Utils.md5(password);

		for(User t:list){
			if(t.getEmail().equalsIgnoreCase(email)){
				if(t.getPassword().equalsIgnoreCase(password)){
					int id = this.userDao.getId(email);
					User user = this.userDao.get(id);
					int state = user.getValidate().getState();
					ServletActionContext.getContext().getSession().put("email", email);
					ServletActionContext.getContext().getSession().put("id",id);
					ServletActionContext.getContext().getSession().put("state",state);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean check(String name,String vcode){
		int id = (Integer)ServletActionContext.getContext().getSession().get("id");
		User user = this.userDao.get(id);
		if(user.getValidate().getVcode().equalsIgnoreCase(vcode)){
			user.getValidate().setState(1);
			user.setName(name);
			this.userDao.saveOrUpdate(user);
			return true;
		}
		return false;
	}

	//退出，清空Session的内容
	public void logout(){
		ServletActionContext.getContext().getSession().put("email", null);
		ServletActionContext.getContext().getSession().put("id",null);
		ServletActionContext.getContext().getSession().put("state",null);
	}
}
