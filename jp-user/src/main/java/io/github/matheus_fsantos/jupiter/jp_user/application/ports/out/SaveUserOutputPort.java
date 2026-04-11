package io.github.matheus_fsantos.jupiter.jp_user.application.ports.out;

import io.github.matheus_fsantos.jupiter.jp_user.application.core.model.request.NewUser;

public interface SaveUserOutputPort {
    void save(NewUser newUser);
}
