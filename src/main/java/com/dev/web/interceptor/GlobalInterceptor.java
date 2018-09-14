package com.dev.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.dev.web.schema.CommonCode;
import com.dev.web.schema.exception.RedirectException;
import com.dev.web.schema.user.UserSession;
import com.dev.web.util.ServiceReferenceContext;

public class GlobalInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		UserSession session = (UserSession) request.getSession().getAttribute(CommonCode.sessionName);
		if(session == null) {
			String ajaxRequest = request.getHeader("X-Requested-With");
			String contextPath = request.getContextPath();
			if(ajaxRequest != null && ajaxRequest.equals("XMLHttpRequest")) {
				throw new RedirectException("/login", "");
			}
			response.sendRedirect(contextPath + "/login");
			return false;
		}
		
		ServiceReferenceContext.setUserSession(session);
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		ServiceReferenceContext.setUserSession(null);
	}
}
