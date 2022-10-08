package com.restsecurity.authservice.repository;

import com.restsecurity.authservice.model.RoleEnum;
import com.restsecurity.authservice.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository <RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleEnum name);
}
