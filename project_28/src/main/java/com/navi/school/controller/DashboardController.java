package com.navi.school.controller;

import com.navi.school.entity.Person;
import com.navi.school.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication, HttpSession session){
        Person person = personRepository.findByEmail(authentication.getName());

        if(person != null) {
            session.setAttribute("loginPerson", person);
            model.addAttribute("username", authentication.getName());
            model.addAttribute("roles", authentication.getAuthorities().toString());
            if(person.getSchoolClass() != null){
                model.addAttribute("enrolledClass", person.getSchoolClass().getName());
            }
            boolean admin = authentication.getAuthorities().toString().contains("ADMIN");
            model.addAttribute("admin", admin);
        }
        return "/dashboard.html";
    }
}
