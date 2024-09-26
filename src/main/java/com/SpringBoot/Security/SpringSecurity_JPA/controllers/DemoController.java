package com.SpringBoot.Security.SpringSecurity_JPA.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String homePage(){
        return "home";
    }
    @GetMapping("/showCustomLogin")
    public String customLoginPage(){
        return "custom-login";
    }

    @GetMapping("/leaders")
    public String showLeadersInfo(){
        return "leader-meeting";
    }

    @GetMapping("/systems")
    public String showAdminInfo(){
        return "admin-meeting";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage(){
        return "custom-error";
    }
}
