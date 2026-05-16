package io.github.matheus_fsantos.jupiter.jp_user.application.core.usecases;

import io.github.matheus_fsantos.jupiter.jp_user.application.core.model.response.User;
import io.github.matheus_fsantos.jupiter.jp_user.application.ports.in.FindAllUsersInputPort;
import io.github.matheus_fsantos.jupiter.jp_user.application.ports.out.FindAllUsersOutputPort;

import java.util.List;

public class FindAllUsersUseCase implements FindAllUsersInputPort {
    private final FindAllUsersOutputPort findAllUsersOutputPort;

    public FindAllUsersUseCase(FindAllUsersOutputPort findAllUsersOutputPort) {
        this.findAllUsersOutputPort = findAllUsersOutputPort;
    }

    @Override
    public List<User> find() {
        return this.findAllUsersOutputPort.find();
    }
}
