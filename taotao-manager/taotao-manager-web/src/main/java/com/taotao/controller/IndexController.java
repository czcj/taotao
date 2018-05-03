package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String login(HttpServletRequest request, HttpServletResponse response){
		return "login";
	}
	@RequestMapping("/index")
	public String index(String name,Integer num){
		return "index";
	}
	@RequestMapping("item-list")
	public String itemList(){
		return "item-list";
	}
	@RequestMapping("item-add")
	public String itemAdd(){
		return "item-add";
	}
	@RequestMapping("item-param-list")
	public String itemParamList(){
		return "item-param-list";
	}
	@RequestMapping("item-param-add")
	public String itemParamAdd(){
		return  "item-param-add";
	}
	@RequestMapping("content-category")
	public String contentCategory(){
		return "content-category";
	}
	@RequestMapping("content")
	public String content(){
		return "content";
	}
	@RequestMapping("content-edit")
	public String contentEdit(){
		return "content-edit";
	}
	@RequestMapping("content-add")
	public String contentAdd(){
		return "content-add";
	}
}
