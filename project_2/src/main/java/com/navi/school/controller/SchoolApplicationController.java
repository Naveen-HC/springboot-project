package com.navi.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SchoolApplicationController {

    @RequestMapping(value = {"", "/", "/home"})
    public String home(Model model){
        model.addAttribute("username", "John Doe");
        return "home.html";
    }
}
