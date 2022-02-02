package com.ksh.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
private static final Logger logger=LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("h.sp")
	public String test(HttpServletRequest request, 
			     Model model, HttpServletResponse response) {
		model.addAttribute("message", "Hello boot!!!");
		logger.info("111");
    return  "NewFile";
	}
}
