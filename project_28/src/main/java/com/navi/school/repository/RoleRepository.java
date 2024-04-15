package com.navi.school.repository;


import com.navi.school.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Integer> {

    Roles getByRoleName(String roleName);
}
