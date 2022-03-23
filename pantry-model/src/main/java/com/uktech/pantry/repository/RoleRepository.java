package com.uktech.pantry.repository;

import com.uktech.pantry.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}