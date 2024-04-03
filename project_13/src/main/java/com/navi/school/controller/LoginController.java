package com.navi.school.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(@RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "logout", required = false) boolean logout,
                        Model model, Authentication authentication){

        String message = null;
        if(error){
            message = "Invalid credentials";
        }
        if(logout){
            message = "Logout successful!!";
        }
        model.addAttribute("message", message);
        return "/login.html";
    }
}
