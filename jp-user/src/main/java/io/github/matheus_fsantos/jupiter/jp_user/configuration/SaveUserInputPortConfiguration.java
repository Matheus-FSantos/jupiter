package io.github.matheus_fsantos.jupiter.jp_user.configuration;

import io.github.matheus_fsantos.jupiter.jp_user.application.core.usecases.SaveUserUseCase;
import io.github.matheus_fsantos.jupiter.jp_user.application.ports.in.SaveUserInputPort;
import io.github.matheus_fsantos.jupiter.jp_user.application.ports.out.SaveUserOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaveUserInputPortConfiguration {
    @Bean
    public SaveUserInputPort saveUserUseCaseConfiguration(SaveUserOutputPort saveUserOutputPort) {
        return new SaveUserUseCase(saveUserOutputPort);
    }
}
