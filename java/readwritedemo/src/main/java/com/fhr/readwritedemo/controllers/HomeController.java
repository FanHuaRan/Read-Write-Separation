package com.fhr.readwritedemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * mvc控制器
 * @author fhr
 * @since 2017/07/29
 */
@Controller
public class HomeController {
	//主页
	@RequestMapping("/index")
	public String index(){
		return "index";
	}

}
