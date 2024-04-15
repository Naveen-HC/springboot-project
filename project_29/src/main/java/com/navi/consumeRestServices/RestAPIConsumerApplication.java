package com.navi.consumeRestServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.navi.consumeRestServices.proxy")
public class RestAPIConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestAPIConsumerApplication.class, args);
	}

}
