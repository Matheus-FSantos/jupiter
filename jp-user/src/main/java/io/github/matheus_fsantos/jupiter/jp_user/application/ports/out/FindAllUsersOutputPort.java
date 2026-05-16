package io.github.matheus_fsantos.jupiter.jp_user.application.ports.out;

import io.github.matheus_fsantos.jupiter.jp_user.application.core.model.response.User;

import java.util.List;

public interface FindAllUsersOutputPort {
    List<User> find();
}
