package com.packt.controller;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class LoginController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private LoginService loginService;


	@RequestMapping({ "/", "/login" })
	public String onStartUp(ModelMap model) {
		return "login";
	}

	@RequestMapping({ "/onLogin" })
	public ModelAndView onLogin(ModelMap model) {
		if (!loginService.isValid()) {
			model.addAttribute("error", "Invalid user name and password");
			return new ModelAndView("login", model);
		}

		String userName = loginService.retrieveName();
		model.addAttribute("name", "Welcome "+userName+"!");
		return new ModelAndView("greetings", model);
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
