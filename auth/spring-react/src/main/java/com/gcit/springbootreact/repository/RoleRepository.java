package com.gcit.springbootreact.repository;

import com.gcit.springbootreact.model.ERole;
import com.gcit.springbootreact.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
