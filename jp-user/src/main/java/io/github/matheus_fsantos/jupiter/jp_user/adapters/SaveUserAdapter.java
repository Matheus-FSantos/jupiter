package io.github.matheus_fsantos.jupiter.jp_user.adapters;

import io.github.matheus_fsantos.jupiter.jp_user.adapters.out.repository.UserRepository;
import io.github.matheus_fsantos.jupiter.jp_user.adapters.out.repository.entity.UserEntity;
import io.github.matheus_fsantos.jupiter.jp_user.application.core.model.request.NewUser;
import io.github.matheus_fsantos.jupiter.jp_user.application.ports.out.SaveUserOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveUserAdapter implements SaveUserOutputPort {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(NewUser newUser) {
        String encodedPassword = this.passwordEncoder.encode(newUser.password());
        UserEntity user = new UserEntity();
        user.setName(newUser.name());
        user.setEmail(newUser.email());
        user.setPassword(encodedPassword);
        user.setRole(1);
        user.setActive(true);
        if(newUser.biography() != null && !newUser.biography().isEmpty())
            user.setBiography(newUser.biography());

        this.userRepository.save(user);
    }
}
