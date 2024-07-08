package com.example.DomainQLNS.Repository;

import com.example.DomainQLNS.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);
}

