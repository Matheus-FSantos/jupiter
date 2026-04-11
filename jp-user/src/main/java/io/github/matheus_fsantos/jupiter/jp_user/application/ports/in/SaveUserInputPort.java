package io.github.matheus_fsantos.jupiter.jp_user.application.ports.in;

import io.github.matheus_fsantos.jupiter.jp_user.application.core.model.request.NewUser;

public interface SaveUserInputPort {
    void save(NewUser newUser);
}
