package com.navi.school.authentication;

import com.navi.school.entity.Person;
import com.navi.school.entity.Roles;
import com.navi.school.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("!prod")
public class NonProdUserAuthentication implements AuthenticationProvider {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        Person person = personRepository.findByEmail(email);
        if(person != null && person.getPersonId()>0){
            return new UsernamePasswordAuthenticationToken(email, null, grantedAuthorities(person.getRoles()));
        } else {
            return null;
        }
    }

    public List<GrantedAuthority> grantedAuthorities(Roles roles){
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + roles.getRoleName());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(grantedAuthority);
        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
