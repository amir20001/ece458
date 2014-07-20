package com.ece458.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class StartController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showLoginPage(HttpSession session, Model model) {

		return new ModelAndView("welcome");
	}

}
