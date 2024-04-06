package com.navi.school.controller;

import com.navi.school.entity.Person;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String loginPage(@RequestParam(value = "error", required = false) boolean error,
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

    @RequestMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
}
