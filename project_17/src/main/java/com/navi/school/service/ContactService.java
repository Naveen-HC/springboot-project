package com.navi.school.service;

import com.navi.school.constants.SchoolConstants;
import com.navi.school.entity.Contact;
import com.navi.school.repository.ContactRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Setter
@Getter
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public boolean saveContact(Contact contact){
        boolean save = false;
        contact.setStatus(SchoolConstants.OPEN);
        contact.setCreatedBy(SchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        int n = contactRepository.saveContact(contact);
        if(n>0){
            save = true;
        }
        return save;
    }

    public List<Contact> getContactMessages() {
        List<Contact> contacts = contactRepository.getContactMessages(SchoolConstants.OPEN);
        return contacts;
    }

    public void updateContactMessages(int contactId,String updatedBy) {
        contactRepository.updateMsgStatus(contactId, updatedBy, SchoolConstants.CLOSE);
    }
}
