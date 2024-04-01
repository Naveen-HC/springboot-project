package com.navi.school.service;

import com.navi.school.model.Contact;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    public boolean saveContact(Contact contact){
        System.out.println(contact);
        return true;
    }
}
