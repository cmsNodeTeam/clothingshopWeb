package com.dev.web.controller.login;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.web.schema.CommonCode;
import com.dev.web.schema.CommonResult;
import com.dev.web.schema.user.UserLogin;
import com.dev.web.schema.user.UserLogin.User;
import com.dev.web.schema.user.UserSession;
import com.dev.web.util.ApiHttpClient;
import com.dev.web.util.GlobalStatus;

@RestController
@RequestMapping(value = "/api/user")
public class LoginController {

	@Autowired
	private ApiHttpClient httpClient;
	
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
		
		request.setAttribute(CommonCode.sessionName, session);
		resp.setCode(CommonCode.REDIRECT);
		resp.setRedirectURL(contextPath + "/index");
		return resp;
	}
	
	@PostMapping("logout")
	public CommonResult userLogout() {
		CommonResult resp = new CommonResult();
		return resp;
	}
	
	@PostMapping("change_language")
	public CommonResult userChangeLanguage() {
		CommonResult resp = new CommonResult();
		return resp;
	}
}
