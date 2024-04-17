package com.navi.school.controller;

import com.navi.school.config.SchoolProperties;
import com.navi.school.entity.Person;
import com.navi.school.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @Value("${school.name}")
    private String schoolName;


    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private Environment environment;

    @Autowired
    private SchoolProperties schoolProperties;

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
        log();
        return "/dashboard.html";
    }

    public void log(){

        log.info("School name: {}", schoolName);
        log.error("School name: " + schoolName);
        log.info("Getting value from environment file: " + environment.getProperty("school.name"));

        log.info(schoolProperties.getName());
        log.info(schoolProperties.getContact().get("page"));
        log.info(schoolProperties.getContact().get("successMsg"));
        log.info(schoolProperties.getBranches().toString());
        log.warn(schoolProperties.getProfile());
        log.error(schoolProperties.getProfile());

    }
}
