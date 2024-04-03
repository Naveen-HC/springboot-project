package com.navi.school.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @RequestMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication){

        model.addAttribute("username",authentication.getName());
        model.addAttribute("roles",authentication.getAuthorities().toString());
        throw new RuntimeException("Oops bad day!!!");
//        return "/dashboard.html";
    }
}
