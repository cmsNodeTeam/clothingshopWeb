package com.dev.web.view.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.web.service.config.CmsApiConfig;
import com.dev.web.util.ServiceReferenceContext;

@Controller
@RequestMapping("/")
public class LoginView {
	
	@Autowired
	private CmsApiConfig apiConfig;

	@GetMapping("login")
	public String gotoLogin() {
		return "login/admin_login";
	}
	
	@GetMapping("index")
	public String gotoIndex(Model model) {
		model.addAttribute("config", apiConfig);
		model.addAttribute("userSession", ServiceReferenceContext.getUserSession());
		return "index/admin_index";
	}
	
	@GetMapping("web/home")
	public String gotoHome() {
		return "index/pages/home";
	}
	
	@GetMapping("web/test")
	public String gotoTest() {
		return "index/pages/test";
	}
	
}
