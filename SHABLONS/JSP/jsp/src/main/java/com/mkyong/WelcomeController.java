package com.mkyong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController{
	@GetMapping("/login")
	public String d() {
		return "login";
	}
}