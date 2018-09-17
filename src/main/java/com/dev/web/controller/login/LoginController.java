package com.dev.web.controller.login;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.dev.web.schema.CommonCode;
import com.dev.web.schema.CommonResult;
import com.dev.web.schema.user.UserLogin;
import com.dev.web.schema.user.UserLogin.User;
import com.dev.web.schema.user.UserSession;
import com.dev.web.util.ApiHttpClient;
import com.dev.web.util.GlobalStatus;
import com.dev.web.util.ServiceReferenceContext;

@RestController
@RequestMapping(value = "/api/user")
public class LoginController {

	@Autowired
	private ApiHttpClient httpClient;
	
	@Resource
    private MessageSource messageSource;
	
	@PostMapping(value = "login")
	public CommonResult doLogin(HttpServletRequest request) {
		CommonResult resp = new CommonResult();
		String contextPath = request.getContextPath();
		UserLogin loginResult = httpClient.post(GlobalStatus.getRemoteUrl(request)
				, UserLogin.class);
		if(loginResult.getCode() != CommonCode.SUCCESS) {
			resp.setCode(loginResult.getCode());
			resp.setMsg(loginResult.getMsg());
			return resp;
		}
		User user = loginResult.getSession();
		
		UserSession session = new UserSession();
		session.setLanguage(user.getLanguage());
		session.setRights(user.getRights());
		session.setSessionid(user.getPassword());
		session.setShopid(user.getShopId());
		session.setUsername(user.getAdminId());
		
		request.getSession().setAttribute(CommonCode.sessionName, session);
		resp.setCode(CommonCode.REDIRECT);
		resp.setRedirectURL(contextPath + "/index");
		return resp;
	}
	
	@PostMapping("logout")
	public CommonResult userLogout(HttpServletRequest request) {
		CommonResult resp = new CommonResult();
		CommonResult logoutResult = httpClient.post(GlobalStatus.getRemoteUrl(request), 
				CommonResult.class);
		if(logoutResult.getCode() != CommonCode.SUCCESS) {
			resp.setCode(logoutResult.getCode());
			resp.setMsg(logoutResult.getMsg());
			return resp;
		}
		String contextPath = request.getContextPath();
		request.getSession().removeAttribute(CommonCode.sessionName);
		resp.setCode(CommonCode.REDIRECT);
		resp.setRedirectURL(contextPath + "/login");
		return resp;
	}
	
	@PostMapping("change_language")
	public CommonResult userChangeLanguage(HttpServletRequest request, HttpServletResponse response) {
		CommonResult resp = new CommonResult();
		LinkedHashMap<String, Object> body = GlobalStatus.getBody(request);
		String langType = body.get("type").toString();
		Locale locale = Locale.CHINA;
		switch (langType) {
			case "en_US":
				locale = Locale.US;
				break;
		}
		LocaleResolver resolver = RequestContextUtils.getLocaleResolver(request);
		resolver.setLocale(request, response, locale);
		UserSession session = ServiceReferenceContext.getUserSession();
		if(session != null) {
			session.setLocalLanguage(locale);
			ServiceReferenceContext.setUserSession(session);
		}
//		String str = messageSource.getMessage("login.title", null, locale);
//		System.out.println(str);
		resp.setCode(CommonCode.SUCCESS);
		return resp;
	}
}
