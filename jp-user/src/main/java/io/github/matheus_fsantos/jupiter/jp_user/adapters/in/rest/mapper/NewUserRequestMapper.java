package io.github.matheus_fsantos.jupiter.jp_user.adapters.in.rest.mapper;

import io.github.matheus_fsantos.jupiter.jp_user.adapters.in.rest.request.NewUserRequest;
import io.github.matheus_fsantos.jupiter.jp_user.application.core.model.request.NewUser;

public class NewUserRequestMapper {
    private NewUserRequestMapper() { }

    public static NewUser toDomain(NewUserRequest user) {
        return new NewUser(
            user.name(),
            user.email(),
            user.password(),
            user.biography()
        );
    }
}
