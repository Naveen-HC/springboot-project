package com.navi.school.authentication;

import com.navi.school.entity.Person;
import com.navi.school.entity.Roles;
import com.navi.school.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthentication implements AuthenticationProvider {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        Person person = personRepository.findByEmail(name);
        if(person != null && passwordEncoder.matches(password, person.getPwd())){
            return new UsernamePasswordAuthenticationToken(authentication.getName(), null,
                    getGrantedAuthorityList(person.getRoles()));
        }
        return null;
    }

    public List<GrantedAuthority> getGrantedAuthorityList(Roles role){
        List<GrantedAuthority> grantedAuthorities =  new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
