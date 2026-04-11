package io.github.matheus_fsantos.jupiter.jp_user.application.core.model;

public enum Role {
    USER(1),
    ADMINISTRATOR(2);
    private final int value;

    Role(int value) {
        this.value = value;
    }

    public static Role getRole(int value) {
        for(Role role : Role.values()) {
            if(role.value == value)
                return role;
        }

        return Role.USER;
    }
}
