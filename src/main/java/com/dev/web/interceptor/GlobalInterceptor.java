package com.dev.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.dev.web.config.CodeEnum;
import com.dev.web.schema.CommonCode;
import com.dev.web.schema.exception.ApiException;
import com.dev.web.schema.exception.RedirectException;
import com.dev.web.schema.user.UserSession;
import com.dev.web.util.ServiceReferenceContext;

public class GlobalInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ajaxRequest = request.getHeader("X-Requested-With");
		String contextPath = request.getContextPath();
		boolean isAjax = ajaxRequest != null && ajaxRequest.equals("XMLHttpRequest");
		UserSession session = (UserSession) request.getSession().getAttribute(CommonCode.sessionName);
		if(session == null) {
			if(isAjax) {
				throw new RedirectException("/login", "");
			}
			response.sendRedirect(contextPath + "/login");
			return false;
		}
		if(response.getStatus() == 404) {
			if(isAjax) {
				throw new ApiException(CodeEnum.ERROR_404.getCode(), CodeEnum.ERROR_404.getMsg());
			}
			response.sendRedirect(contextPath + "/index");
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
