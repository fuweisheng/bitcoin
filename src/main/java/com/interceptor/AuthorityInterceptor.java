package com.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 取得请求相关的ActionContext实例
		ActionContext ctx = invocation.getInvocationContext();
		Map<String,Object> session = ctx.getSession();
		//取出名为name的session属性 
		String email = (String)session.get("email");
		Integer state = (Integer)session.get("state");
		if(state == null){
			ctx.put("tip", "请登录,再进行操作...");
			return "login";
		}
		 //如果没有登陆，或者登陆所有的用户名不是admin，都返回重新登陆 
		if(email!=null && state==1){
			return invocation.invoke();
		}
		//将服务器提示设置成一个HttpServletRequest属性 
		if(email!=null && state==0){
			ctx.put("tip", "请先验证,再进行操作...");
			return "validate";
		}
		ctx.put("tip", "请登录,再进行操作...");
		return "login";
	}

}
