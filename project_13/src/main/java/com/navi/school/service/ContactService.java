package com.navi.school.service;

import com.navi.school.model.Contact;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Service
@Setter
@Getter
//@RequestScope
//@SessionScope
//@ApplicationScope
public class ContactService {

    private int count = 1;

    public boolean saveContact(Contact contact){
        System.out.println(contact);
        System.out.println(count);
        return true;
    }
}
