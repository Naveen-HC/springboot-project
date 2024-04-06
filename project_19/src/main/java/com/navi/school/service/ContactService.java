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
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

        contact.setStatus(SchoolConstants.OPEN);
        contact.setCreatedBy(SchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        Contact savedContact = contactRepository.save(contact);
       return savedContact.equals(contact);
    }

    public List<Contact> getContactMessages() {
        Iterable<Contact> iterableContacts = contactRepository.findByStatus(SchoolConstants.OPEN);
        List<Contact> contacts = StreamSupport.stream(iterableContacts.spliterator(),false)
                .collect(Collectors.toList());
        return contacts;
    }

    public void updateContactMessages(int contactId,String updatedBy) {

        Optional<Contact> contact = contactRepository.findById(contactId);
        if(contact.isPresent()){
            contact.ifPresent(contact1 ->
            {contact1.setStatus(SchoolConstants.CLOSE);
                contact1.setUpdatedBy(updatedBy);
                contact1.setUpdatedAt(LocalDateTime.now());
            });
            contactRepository.save(contact.get());
        } else {
            throw new RuntimeException("No contact found for contactId: " + contactId);
        }
    }

}
