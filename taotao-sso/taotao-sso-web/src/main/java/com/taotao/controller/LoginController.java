package com.taotao.controller;

import com.taotao.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private UserTokenService userTokenService;
    @RequestMapping("/")
    public String index(){
        return "Index";
    }
    @RequestMapping("/addtoken")
    public String login(String phone, String password, HttpServletRequest request, HttpServletResponse response){
        String token = userTokenService.setUserToken(phone,password);
        if(token != null){
            Cookie cookie = new Cookie("loginToken",token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "Index";
        }else{
            return "login";
        }
    }
    @RequestMapping("/login.html")
    public String login( HttpServletRequest request, HttpServletResponse response){
        return "/login";
    }
}
