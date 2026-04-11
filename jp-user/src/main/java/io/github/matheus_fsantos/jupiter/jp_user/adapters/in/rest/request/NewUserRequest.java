package io.github.matheus_fsantos.jupiter.jp_user.adapters.in.rest.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewUserRequest(
    @NotBlank(message = "The name is required.")
    @Size(min = 3, max = 150, message = "The name should contain between 3 and 150 characters.")
    String name,
    @NotBlank(message = "The email is required.")
    @Email(message = "Enter a valid email address.")
    @Size(min = 3, max = 150, message = "The email should contain between 3 and 150 characters.")
    String email,
    @NotBlank(message = "The password is required.")
    @Size(min = 4, max = 30, message = "The password should contain between 3 and 30 characters.")
    String password,
    @Size(max = 255, message = "The biography must not exceed 255 characters.")
    String biography
) { }
