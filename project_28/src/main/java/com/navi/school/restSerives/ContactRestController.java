package com.navi.school.restSerives;

import com.navi.school.constants.SchoolConstants;
import com.navi.school.entity.Contact;
import com.navi.school.model.Response;
import com.navi.school.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "api/rest", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@CrossOrigin("*")
public class ContactRestController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/getContactDetailsByParam")
//    @ResponseBody
    public List<Contact> getContactDetailsByParam(@RequestParam String status) {

        List<Contact> contacts = contactRepository.findByStatus(status);
        return contacts;
    }

    @GetMapping("/getContactDetailsByBody")
//    @ResponseBody
    public List<Contact> getContactDetailsByBody(@RequestBody Contact contact) {

        if (contact != null) {
            return contactRepository.findByStatus(contact.getStatus());
        } else {
            return List.of();
        }
    }

    @PostMapping("/saveContactDetails")
    public ResponseEntity<Response> saveContactDetails(@RequestHeader(name = "invocationInfo") String invocationInfo,
                                                       @Valid @RequestBody Contact contact) {

        System.out.println("invocationInfo: " + invocationInfo);
        contact.setStatus(SchoolConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        Response response = new Response();
        response.setStatusCode("200");
        response.setMessage("Details are saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isDetailsSaved", "true")
                .body(response);

//        throw new RuntimeException("nooooooooooooooooooooo");

    }

    @PostMapping("/deleteContactDetails")
    public ResponseEntity<Response> deleteContactDetails(RequestEntity<Contact> requestEntity) {

        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((key, value) ->
        {
            log.info(String.format(
                    "Header '%s' = %s", key, String.join("|", value)
            ));
        });
        Contact contact = requestEntity.getBody();
        Response response = new Response();
        if (contact != null && contact.getContactId() > 0 && contactRepository.existsById(contact.getContactId())) {
            contactRepository.deleteById(contact.getContactId());
            response.setStatusCode("200");
            response.setMessage("Contact details deleted successfully.");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("isDeleted", "true")
                    .body(response);
        } else {
            response.setStatusCode("400");
            response.setMessage("error!! no contact id found");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .header("isDeleted", "false")
                    .body(response);
        }

    }
}
