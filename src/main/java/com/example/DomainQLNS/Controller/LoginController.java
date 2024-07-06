package com.example.DomainQLNS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login(){
        return "/Login/index";
    }

    @GetMapping("/Layout")
    public String layout(){
        return "Layout";
    }
}
