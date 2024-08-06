package com.alex96jvm.authservice.repositories;

import com.alex96jvm.authservice.models.entities.RoleEntity;
import com.alex96jvm.authservice.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

}
