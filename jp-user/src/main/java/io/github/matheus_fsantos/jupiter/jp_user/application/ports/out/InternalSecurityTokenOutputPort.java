package io.github.matheus_fsantos.jupiter.jp_user.application.ports.out;

public interface InternalSecurityTokenOutputPort {
    boolean isValid(String token);
}
