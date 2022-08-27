package com.example.fullstackbookjwtspringboot.repository;

import com.example.fullstackbookjwtspringboot.model.ERole;
import com.example.fullstackbookjwtspringboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
