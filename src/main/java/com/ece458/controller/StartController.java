package com.ece458.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ece458.dao.TestDao;
import com.ece458.domain.Data;

@Controller
@RequestMapping("/")
public class StartController {

	@Autowired
	TestDao testDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showLoginPage(HttpSession session, Model model) {
		model.addAttribute("data", null);
		return new ModelAndView("welcome");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/lookup")
	public ModelAndView doLookup(HttpSession session, Model model,
			@RequestParam("domainName") String domainName) {
		Data data = testDao.getip(domainName);
		System.out.println(data);
		model.addAttribute("data", data);
		return new ModelAndView("lookup");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/lookup")
	public ModelAndView viewLookup(HttpSession session, Model model) {

		return new ModelAndView("lookup");
	}

}
