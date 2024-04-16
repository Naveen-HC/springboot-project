package com.navi.school.controller;

import com.navi.school.entity.Address;
import com.navi.school.entity.Person;
import com.navi.school.model.Profile;
import com.navi.school.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("profileControllerBean")
public class ProfileController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/displayProfile")
    public ModelAndView displayProfile(@PathVariable(required = false) boolean updated, HttpSession session){

        Profile profile = new Profile();
        Person person = (Person) session.getAttribute("loginPerson");
        if(person != null) {
            profile.setName(person.getPersonName());
            profile.setMobileNumber(person.getMobileNumber());
            profile.setEmail(person.getEmail());
            if (person.getAddress() != null && person.getAddress().getAddressId() > 0) {
                profile.setAddress1(person.getAddress().getAddress1());
                profile.setAddress2(person.getAddress().getAddress2());
                profile.setCity(person.getAddress().getCity());
                profile.setState(person.getAddress().getState());
                profile.setZipCode(person.getAddress().getZipCode());
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("profile", profile);
        modelAndView.setViewName("profile");
        if(updated)
            modelAndView.addObject("updatedMsg", "Profile is updated successfully!!");
        return modelAndView;
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@Valid @ModelAttribute Profile profile, HttpSession session, Errors errors){

        if(errors.hasErrors())
                return "/profile.html";

        Person person = (Person) session.getAttribute("loginPerson");
        person.setPersonName(profile.getName());
        person.setMobileNumber(profile.getMobileNumber());
        person.setEmail(profile.getEmail());

        if(person.getAddress() == null)
            person.setAddress(new Address());

        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());

        Person svaedPerson = personRepository.save(person);
        session.setAttribute("loginPerson", svaedPerson);
        return "redirect:/displayProfile?updated=true";
    }
}
