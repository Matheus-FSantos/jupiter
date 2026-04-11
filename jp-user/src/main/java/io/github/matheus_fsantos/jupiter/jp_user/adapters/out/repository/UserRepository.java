package io.github.matheus_fsantos.jupiter.jp_user.adapters.out.repository;

import io.github.matheus_fsantos.jupiter.jp_user.adapters.out.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> { }
