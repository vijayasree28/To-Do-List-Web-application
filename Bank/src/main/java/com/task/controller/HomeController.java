package com.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public ModelAndView IndexPage() {
		System.out.println("In Home Controller");
	
		return new ModelAndView("index");

	}
}