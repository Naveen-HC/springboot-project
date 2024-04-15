package com.navi.consumeRestServices.proxy;

import com.navi.consumeRestServices.config.ProjectConfiguration;
import com.navi.consumeRestServices.model.Contact;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "http://localhost:8080/api/rest", configuration = ProjectConfiguration.class,
        name = "contact")
public interface ContactProxy {

    @GetMapping("/getContactDetailsByParam")
    @Headers(value = "Content-Type: application/json")
    public List<Contact> getContactDetailsByParam(@RequestParam String status);
}
