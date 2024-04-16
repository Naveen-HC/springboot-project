package com.navi.school.controller;

import com.navi.school.entity.Contact;
import com.navi.school.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ContactController {


    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String contact(Model model){
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }


//    @PostMapping("/saveContact")
//    public ModelAndView saveContact(@RequestParam String name, @RequestParam String mobileNum,
//                                    @RequestParam String email, @RequestParam String subject,
//                                    @RequestParam String message){
//        return new ModelAndView("redirect:/contact");
//    }

    @PostMapping("/saveContact")
    public String saveContact(@Valid @ModelAttribute Contact contact, Errors errors){
        if(errors.hasErrors()){
            log.info("Contact form submission failed due to: " + errors.toString());
            return "contact.html";
        }
        contactService.saveContact(contact);
        return "redirect:/contact";
    }
}
