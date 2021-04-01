package com.numberone.webshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @RequestMapping("/")
    public void index(HttpServletResponse response){
        response.setHeader("Location", "http://landingpage-ucllteam01.ocp-ucll-40cb0df2b03969eabb3fac6e80373775-0000.eu-de.containers.appdomain.cloud/");
        response.setStatus(302);
    }
}
