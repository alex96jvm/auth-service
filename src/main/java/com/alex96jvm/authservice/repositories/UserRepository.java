package com.alex96jvm.authservice.repositories;

import com.alex96jvm.authservice.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByLogin(String username);
}
