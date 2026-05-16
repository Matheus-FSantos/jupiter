package io.github.matheus_fsantos.jupiter.jp_user.adapters;

import io.github.matheus_fsantos.jupiter.jp_user.adapters.out.repository.UserRepository;
import io.github.matheus_fsantos.jupiter.jp_user.adapters.out.repository.entity.UserEntity;
import io.github.matheus_fsantos.jupiter.jp_user.adapters.out.repository.mapper.UserEntityMapper;
import io.github.matheus_fsantos.jupiter.jp_user.application.core.model.response.User;
import io.github.matheus_fsantos.jupiter.jp_user.application.ports.out.FindAllUsersOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindAllUsersAdapter implements FindAllUsersOutputPort {
    private final UserRepository userRepository;

    @Override
    public List<User> find() {
        List<UserEntity> userEntityList = this.userRepository.findAll();
        return userEntityList.stream().map(UserEntityMapper::toDomain).toList();
    }
}
