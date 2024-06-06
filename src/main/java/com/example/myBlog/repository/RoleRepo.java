package com.example.myBlog.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {


    Optional<Role> findByRoleName(String roleName);



}

