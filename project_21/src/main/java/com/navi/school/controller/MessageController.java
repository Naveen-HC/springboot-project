package com.navi.school.controller;

import com.navi.school.entity.Contact;
import com.navi.school.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MessageController {

    private final ContactService contactService;

    @Autowired
    public MessageController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/displayMessages")
    public ModelAndView getContactMessages(){
        List<Contact> contactMessages = contactService.getContactMessages();
        ModelAndView modelAndView = new ModelAndView("/messages");
        modelAndView.addObject("contactMessages", contactMessages);
        return modelAndView;
    }

    @RequestMapping(value = "/updateMessage", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateContactMessages(@RequestParam(required = false) String contactId, Authentication auth){

        if(contactId != null){
            contactService.updateContactMessages(Integer.parseInt(contactId), auth.getName());
        } else {
            throw new RuntimeException("Contact id is null");
        }
        return "redirect:/displayMessages";
    }
}
