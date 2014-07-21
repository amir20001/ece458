package com.ece458.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		List<Data> allData = testDao.getAll();
		System.out.println("data size: " + allData.size());
		for (Data data : allData) {
			System.out.println(data);
		}
		return new ModelAndView("welcome");
	}

}
