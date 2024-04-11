package com.navi.school.controller;

import com.navi.school.entity.Courses;
import com.navi.school.entity.Person;
import com.navi.school.entity.SchoolClass;
import com.navi.school.repository.CoursesRepository;
import com.navi.school.repository.PersonRepository;
import com.navi.school.repository.SchoolClassRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CoursesRepository coursesRepository;

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

    @DeleteMapping("/deleteClass")
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

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(){
        ModelAndView modelAndView = new ModelAndView("courses_secure");
        modelAndView.addObject("course", new Courses());
        List<Courses> courses = coursesRepository.findAll();
        modelAndView.addObject("courses",courses);
        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public String addNewCourse(@ModelAttribute Courses course){
        Courses savedCourse = coursesRepository.save(course);

        return "redirect:/admin/displayCourses";
    }

    @GetMapping("/deleteCourse")
    public String deleteCourse(@RequestParam(required = false) int courseId){
        if(courseId > 0){
            coursesRepository.deleteById(courseId);
        }
        return "redirect:/admin/displayCourses";
    }

    @GetMapping("/viewStudents")
    public  ModelAndView viewStudents(@RequestParam(required = false) int courseId,
                                      @RequestParam(required = false) String error, HttpSession session){
        ModelAndView modelAndView = new ModelAndView("course_students");
        modelAndView.addObject("person", new Person());
        Optional<Courses> courseObject = coursesRepository.findById(courseId);
        Courses course = courseObject.get();
        modelAndView.addObject("course", course);
        session.setAttribute("course", course);
        if(error != null) {
            modelAndView.addObject("error", "Invalid student email address");
        }
        return modelAndView;
    }

    @PostMapping("/addStudentToCourse")
    public String addStudentToCourse(@ModelAttribute Person person, HttpSession session){
        Person savedPerson = personRepository.findByEmail(person.getEmail());
        Courses course = (Courses)session.getAttribute("course");
        if (savedPerson == null || savedPerson.getPersonId() == 0){
            return "redirect:/admin/viewStudents?courseId="+course.getCourseId()+"&error=true";
        }
//        List<Courses> courses = new ArrayList<>();
//        courses.add(course);
//        savedPerson.setCourses(courses);
//        personRepository.save(savedPerson);
        List<Person> personList = new ArrayList<>();
        personList.add(savedPerson);
        course.setPersons(personList);
        coursesRepository.save(course);
        return "redirect:/admin/viewStudents?courseId="+course.getCourseId();
    }

    @GetMapping("/deleteStudentFromCourse")
    public String deleteStudentFromCourse(@RequestParam int personId, HttpSession session){
        Person person = (personRepository.findById(personId)).get();
        Courses course = (Courses) session.getAttribute("course");
        course.getPersons().remove(person);
        coursesRepository.save(course);
        return "redirect:/admin/viewStudents?courseId="+course.getCourseId();
    }
}