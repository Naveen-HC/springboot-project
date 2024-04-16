package com.navi.school.service;

import com.navi.school.constants.SchoolConstants;
import com.navi.school.entity.Person;
import com.navi.school.entity.Roles;
import com.navi.school.repository.PersonRepository;
import com.navi.school.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean savePerson(Person person){
        Roles role = roleRepository.getByRoleName(SchoolConstants.ROLE_STUDENT);
        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        Person person1 = personRepository.save(person);
        return person1 != null;
    }
}
