package io.github.matheus_fsantos.jupiter.jp_user.application.core.model.request;

public record NewUser(
    String name,
    String email,
    String password,
    String biography
) { }
