package com.navi.school.controller;

import com.navi.school.model.Contact;
import com.navi.school.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {


    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String contact(){
        return "contact.html";
    }


//    @PostMapping("/saveContact")
//    public ModelAndView saveContact(@RequestParam String name, @RequestParam String mobileNum,
//                                    @RequestParam String email, @RequestParam String subject,
//                                    @RequestParam String message){
//        return new ModelAndView("redirect:/contact");
//    }

    @PostMapping("/saveContact")
    public ModelAndView saveContact(Contact contact){

        contactService.saveContact(contact);
        return  new ModelAndView("contact.html");
    }
}
