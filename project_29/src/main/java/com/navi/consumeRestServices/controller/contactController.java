package com.navi.consumeRestServices.controller;

import com.navi.consumeRestServices.model.Contact;
import com.navi.consumeRestServices.model.Response;
import com.navi.consumeRestServices.proxy.ContactProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping()
public class contactController {

    @Autowired
    private ContactProxy contactProxy;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @GetMapping("/getContactDetails")
    public List<Contact> getContactDetailsByParam(@RequestParam String status){
        return contactProxy.getContactDetailsByParam("close");
    }

    @PostMapping("/saveContactDetails")
    public ResponseEntity<Response> saveContactDetails(@RequestBody Contact contact) {
        String url = "http://localhost:8080/api/rest/saveContactDetails";
        HttpHeaders headers = new HttpHeaders();
        headers.add("invocationInfo", "RestTemplate");
        ResponseEntity<Response> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity<>(contact, headers), Response.class);
        return responseEntity;
    }

    @PostMapping("/deleteContactDetails")
    public Mono<Response> deleteContactDetails(RequestEntity<Contact> requestEntity) {
        String uri = "http://localhost:8080/api/rest/deleteContactDetails";
        Contact contact =  requestEntity.getBody();
        return webClient.post().uri(uri)
                .header("invocationInfo", "WebClient")
                .body(Mono.just(contact), Contact.class)
                .retrieve()
                .bodyToMono(Response.class);
    }
}
