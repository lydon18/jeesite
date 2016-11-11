/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 表单验证（包含验证码）过滤类
 * @author ThinkGem
 * @version 2014-5-19
 */
@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
	private static final Logger log = LoggerFactory.getLogger(FormAuthenticationFilter.class);

	public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";
	public static final String DEFAULT_MOBILE_PARAM = "mobileLogin";
	public static final String DEFAULT_MESSAGE_PARAM = "message";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	private String mobileLoginParam = DEFAULT_MOBILE_PARAM;
	private String messageParam = DEFAULT_MESSAGE_PARAM;

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password==null){
			password = "";
		}
		boolean rememberMe = isRememberMe(request);
		String host = StringUtils.getRemoteAddr((HttpServletRequest)request);
		String captcha = getCaptcha(request);
		boolean mobile = isMobileLogin(request);
		return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha, mobile);
	}

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	public String getMobileLoginParam() {
		return mobileLoginParam;
	}
	
	protected boolean isMobileLogin(ServletRequest request) {
        return WebUtils.isTrue(request, getMobileLoginParam());
    }
	
	public String getMessageParam() {
		return messageParam;
	}
	
	

	/**
	 * 登录成功之后跳转URL
	 */
	public String getSuccessUrl() {
		return super.getSuccessUrl();
	}
	
	@Override
	protected void issueSuccessRedirect(ServletRequest request,
			ServletResponse res) throws Exception {
//		Principal p = UserUtils.getPrincipal();
//		if (p != null && !p.isMobileLogin()){
		HttpServletResponse response = (HttpServletResponse) res;
			 WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
//		}else{
//			super.issueSuccessRedirect(request, response);
//		}
	}

	/**
	 * 登录失败调用事件
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request, ServletResponse response) {
		String className = e.getClass().getName(), message = "";
		if (IncorrectCredentialsException.class.getName().equals(className)
				|| UnknownAccountException.class.getName().equals(className)){
			message = "用户名或密码错误, 请重试.";
		}
		else if (e.getMessage() != null && StringUtils.startsWith(e.getMessage(), "msg:")){
			message = StringUtils.replace(e.getMessage(), "msg:", "");
		}
		else{
			message = "系统出现点问题，请稍后再试！";
			e.printStackTrace(); // 输出到控制台
		}
		 try {
	            response.setCharacterEncoding("UTF-8");
	            PrintWriter out = response.getWriter();
	            message = e.getClass().getSimpleName();
	            if ("IncorrectCredentialsException".equals(message)) {
	                out.println("{success:false,message:'密码错误'}");
	            } else if ("UnknownAccountException".equals(message)) {
	                out.println("{success:false,message:'账号不存在'}");
	            } else if ("LockedAccountException".equals(message)) {
	                out.println("{success:false,message:'账号被锁定'}");
	            } else {
	                out.println("{success:false,message:'未知错误'}");
	            }
	            out.flush();
	            out.close();
	        } catch (IOException e1) {
	// TODO Auto-generated catch block
	            e1.printStackTrace();
	        }
//        return true;
        return false;
	}
	
	  /**
     * 当登录成功
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
//		 HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//	     HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//	     issueSuccessRedirect(request, response);
//	     if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest
//	                .getHeader("X-Requested-With"))) {// 不是ajax请求
//	        } else {
//	            httpServletResponse.setCharacterEncoding("UTF-8");
//	            PrintWriter out = httpServletResponse.getWriter();
//	            out.println("{\"success\":true,\"message\":\"登入成功\"}");
//	            out.flush();
//	            out.close();
//	        }
	        return true;
	}
	
//	@Override
//	protected boolean onAccessDenied(ServletRequest request,
//			ServletResponse response) throws Exception {
//		if (isLoginRequest(request, response)) {
//		     if(this.isLoginSubmission(request, response)) {
//	                if(log.isTraceEnabled()) {
//	                    log.trace("Login submission detected.  Attempting to execute login.");
//	                }
//
//	                return this.executeLogin(request, response);
//	            } else {
//	                if(log.isTraceEnabled()) {
//	                    log.trace("Login page view.");
//	                }
//
//	                return true;
//	            }
//        } else {
//            if (log.isTraceEnabled()) {
//                log.trace("Attempting to access a path which requires authentication.");
//            }
//            HttpServletResponse res = (HttpServletResponse) response;
//            res.setStatus(401);
//            com.thinkgem.jeesite.common.utils.WebUtils.sendJson(res, "success:false,message:未登录,无操作权限.");
//            return false;
//        }
//	}
}