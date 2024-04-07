package com.navi.school.service;

import com.navi.school.constants.SchoolConstants;
import com.navi.school.entity.Person;
import com.navi.school.entity.Roles;
import com.navi.school.repository.PersonRepository;
import com.navi.school.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    public boolean savePerson(Person person){
        Roles role = roleRepository.getByRoleName(SchoolConstants.ROLE_STUDENT);
        person.setRoles(role);
        Person person1 = personRepository.save(person);
        return person1 != null;
    }
}
