package com.navi.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SchoolApplicationController {

    @RequestMapping("/home")
    public String home(){
        return "home.html";
    }
}
