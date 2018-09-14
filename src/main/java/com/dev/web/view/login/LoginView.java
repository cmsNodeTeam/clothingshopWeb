package com.dev.web.view.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginView {

	@GetMapping("login")
	public String gotoLogin() {
		return "login/admin_login";
	}
	
	@GetMapping("index")
	public String gotoIndex() {
		return "index/admin_index";
	}
}
