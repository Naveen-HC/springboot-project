package com.navi.school.config;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/","/home").permitAll()
                        .requestMatchers( "/contact").permitAll()
                        .requestMatchers("/saveContact").permitAll()
                        .requestMatchers("/Courses").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/assets/**").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());

        return http.build();
    }
}