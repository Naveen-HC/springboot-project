package com.navi.school.controller;

import com.navi.school.entity.Person;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("student")
public class StudentController {

    @GetMapping("displayCourses")
    public ModelAndView displayCourses(HttpSession session){
        Person person = (Person)session.getAttribute("loginPerson");
        ModelAndView modelAndView = new ModelAndView("courses_enrolled");
        modelAndView.addObject("person", person);
        return modelAndView;
    }
}
