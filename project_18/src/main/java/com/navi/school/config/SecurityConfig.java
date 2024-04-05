package com.navi.school.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Deprecated
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterchain(HttpSecurity http) throws Exception{
         http.csrf(csrf -> csrf.ignoringRequestMatchers("/saveContact"))
                .authorizeHttpRequests(request ->
                request.requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/displayMessages").hasRole("ADMIN")
                        .requestMatchers("/updateMessage").hasRole("ADMIN")
                        .requestMatchers("/","/home").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/saveContact").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll())
                .formLogin(loginConfig ->
                        loginConfig.loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true"))
//              .logout(logoutConfig -> logoutConfig.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());
        http.headers().frameOptions().disable();
         return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .roles("ADMIN","USER")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
