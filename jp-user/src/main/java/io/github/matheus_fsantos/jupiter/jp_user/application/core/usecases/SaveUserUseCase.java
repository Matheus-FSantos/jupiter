package io.github.matheus_fsantos.jupiter.jp_user.application.core.usecases;

import io.github.matheus_fsantos.jupiter.jp_user.application.core.model.request.NewUser;
import io.github.matheus_fsantos.jupiter.jp_user.application.ports.in.SaveUserInputPort;
import io.github.matheus_fsantos.jupiter.jp_user.application.ports.out.SaveUserOutputPort;

import java.util.logging.Logger;

public class SaveUserUseCase implements SaveUserInputPort {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private final SaveUserOutputPort saveUserOutputPort;

    public SaveUserUseCase(SaveUserOutputPort saveUserOutputPort) {
        this.saveUserOutputPort = saveUserOutputPort;
    }

    @Override
    public void save(NewUser newUser) {
        this.saveUserOutputPort.save(newUser);
    }
}
