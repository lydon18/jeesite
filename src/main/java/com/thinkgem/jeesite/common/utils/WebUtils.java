package com.thinkgem.jeesite.common.utils;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.mapper.JsonMapper;

public class WebUtils {
	public static boolean isAjax(ServletRequest request){
		if("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)
                .getHeader("X-Requested-With"))){
			return true;
		}
		return false;
	}
	
	public static void sendJson(HttpServletResponse response, Object object){
		try {
			response.reset();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("utf-8");
			response.getWriter().print(JsonMapper.toJsonString(object));
		} catch (IOException e) {
			
		}
	}
}
