package com.navi.school.controller;

import com.navi.school.entity.Person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/register")
    public String displayRegisterPage(Model model){

        Person person = new Person();
        model.addAttribute("person", person);
        return "register.html";
    }
}
