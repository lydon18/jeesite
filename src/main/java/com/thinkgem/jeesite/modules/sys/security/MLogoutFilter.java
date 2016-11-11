package com.thinkgem.jeesite.modules.sys.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.jeesite.common.result.Result;
import com.thinkgem.jeesite.common.utils.WebUtils;

public class MLogoutFilter extends LogoutFilter{
	 private static final Logger log = LoggerFactory.getLogger(MLogoutFilter.class);
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		 Subject subject = getSubject(request, response);
		 HttpServletResponse res = (HttpServletResponse)response;
	        try {
	            subject.logout();
	            Result result = new Result();
	            result.set(Result.SUCCESS, "退出登录");
	            WebUtils.sendJson(res, result);
	        } catch (SessionException ise) {
	            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
	        }
	        return false;
	}
}
