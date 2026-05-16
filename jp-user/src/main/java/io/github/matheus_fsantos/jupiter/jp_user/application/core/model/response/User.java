package io.github.matheus_fsantos.jupiter.jp_user.application.core.model.response;

import io.github.matheus_fsantos.jupiter.jp_user.application.core.model.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private UUID id;
    private Role role;
    private String name;
    private String email;
    private String password;
    private String biography;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User() { }

    public User(UUID id, Role role, String name, String email, String password, String biography, Boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.email = email;
        this.password = password;
        this.biography = biography;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
