package com.dev.web.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.web.schema.CommonResult;
import com.dev.web.util.ApiHttpClient;
import com.dev.web.util.GlobalStatus;

@RequestMapping(value = "/api/user")
public class LoginController {

	@Autowired
	private ApiHttpClient httpClient;
	
	@PostMapping(value = "login")
	public CommonResult doLogin(HttpServletRequest request) {
		return httpClient.post(request.getRequestURI(), GlobalStatus.getBody(request), 
				CommonResult.class);
	}
}
