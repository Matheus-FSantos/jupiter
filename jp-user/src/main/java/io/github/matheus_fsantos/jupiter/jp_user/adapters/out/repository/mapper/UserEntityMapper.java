package io.github.matheus_fsantos.jupiter.jp_user.adapters.out.repository.mapper;

import io.github.matheus_fsantos.jupiter.jp_user.adapters.out.repository.entity.UserEntity;
import io.github.matheus_fsantos.jupiter.jp_user.application.core.model.Role;
import io.github.matheus_fsantos.jupiter.jp_user.application.core.model.response.User;

public class UserEntityMapper {
    private UserEntityMapper() { }

    public static User toDomain(UserEntity userEntity) {
        Role role = Role.getRole(userEntity.getRole());
        return new User(userEntity.getId(), role, userEntity.getName(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getBiography(), userEntity.getActive(), userEntity.getCreatedAt(), userEntity.getUpdatedAt());
    }
}
