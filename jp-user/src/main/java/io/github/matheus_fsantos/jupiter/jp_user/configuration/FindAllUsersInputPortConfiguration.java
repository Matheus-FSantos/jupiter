package io.github.matheus_fsantos.jupiter.jp_user.configuration;

import io.github.matheus_fsantos.jupiter.jp_user.application.core.usecases.FindAllUsersUseCase;
import io.github.matheus_fsantos.jupiter.jp_user.application.ports.in.FindAllUsersInputPort;
import io.github.matheus_fsantos.jupiter.jp_user.application.ports.out.FindAllUsersOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindAllUsersInputPortConfiguration {
    @Bean
    FindAllUsersInputPort findAllUsersUseCaseConfiguration(FindAllUsersOutputPort findAllUsersOutputPort) {
        return new FindAllUsersUseCase(findAllUsersOutputPort);
    }
}
