package io.github.matheus_fsantos.jupiter.jp_user.application.ports.in;

import io.github.matheus_fsantos.jupiter.jp_user.application.core.model.response.User;

import java.util.List;

public interface FindAllUsersInputPort {
    List<User> find();
}
