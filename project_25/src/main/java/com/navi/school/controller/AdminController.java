package com.navi.school.controller;

import com.navi.school.entity.Person;
import com.navi.school.entity.SchoolClass;
import com.navi.school.repository.PersonRepository;
import com.navi.school.repository.SchoolClassRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.SimpleTimeZone;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/displayClasses")
    public ModelAndView displayClass() {

        List<SchoolClass> schoolClasses = schoolClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes");
        modelAndView.addObject("schoolClasses", schoolClasses);
        modelAndView.addObject("schoolClass", new SchoolClass());
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public String addNewClass(@ModelAttribute SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
        return "redirect:/admin/displayClasses";
    }

    @GetMapping("/deleteClass")
    public String deleteClass(@RequestParam int classId) {
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(classId);
        if (schoolClass.isPresent()) {
            for (Person person : schoolClass.get().getPersons()) {
                person.setSchoolClass(null);
                personRepository.save(person);
            }
        }
        schoolClassRepository.deleteById(classId);
        return "redirect:/admin/displayClasses";
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(@RequestParam int classId,
                                        @RequestParam(required = false) String error,
                                        HttpSession session) {

        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(classId);
        ModelAndView modelAndView = new ModelAndView("students");
        modelAndView.addObject("schoolClass",schoolClass.get());
        modelAndView.addObject("person", new Person());
        if(error != null){
            modelAndView.addObject("errorMsg", "Invalid student email address");
        }
        session.setAttribute("schoolClass", schoolClass.get());
        return modelAndView;
    }


    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute Person person, HttpSession session) {

        person = personRepository.findByEmail(person.getEmail());
        SchoolClass schoolClass = (SchoolClass) session.getAttribute("schoolClass");
        if(person == null){
            return "redirect:/admin/displayStudents?error=true&classId="+schoolClass.getClassId();
        }
        person.setSchoolClass(schoolClass);
        personRepository.save(person);
        return "redirect:/admin/displayStudents?classId="+person.getSchoolClass().getClassId();
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam int personId){

        Optional<Person> person = personRepository.findById(personId);
        Person person1 = person.get();
        int classId = person1.getSchoolClass().getClassId();
                person1.setSchoolClass(null);
        personRepository.save(person1);
        return "redirect:/admin/displayStudents?classId="+classId;
    }
}



