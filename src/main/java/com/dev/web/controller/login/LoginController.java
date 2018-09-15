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
		UserLogin loginResult = httpClient.post(GlobalStatus.getRemoteUrl(request)
				, UserLogin.class);
		if(loginResult.getCode() != CommonCode.SUCCESS) {
			resp.setCode(loginResult.getCode());
			resp.setMsg(loginResult.getMsg());
			return resp;
		}
		UserSession session = new UserSession();
		User user = loginResult.getSession();
		System.out.println(user.getAdminId());
//		session.setLanguage(language);
		return resp;
	}
}
