package com.navi.school.controller;

import com.navi.school.constants.SchoolConstants;
import com.navi.school.entity.Contact;
import com.navi.school.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MessageController {

    private final ContactRepository contactRepository;

    @Autowired
    public MessageController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/displayMessages")
    public ModelAndView getContactMessages(){
        List<Contact> contactMessages = contactRepository.getContactMessages(SchoolConstants.OPEN);
        ModelAndView modelAndView = new ModelAndView("/messages");
        modelAndView.addObject("contactMessages", contactMessages);
        return modelAndView;
    }
}
