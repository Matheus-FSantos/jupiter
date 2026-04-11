package io.github.matheus_fsantos.jupiter.jp_user.adapters.in.rest;

import io.github.matheus_fsantos.jupiter.jp_user.adapters.in.rest.mapper.NewUserRequestMapper;
import io.github.matheus_fsantos.jupiter.jp_user.adapters.in.rest.request.NewUserRequest;
import io.github.matheus_fsantos.jupiter.jp_user.application.core.model.request.NewUser;
import io.github.matheus_fsantos.jupiter.jp_user.application.ports.in.SaveUserInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE })
public class UserController {
    private final SaveUserInputPort saveUserInputPort;

    @PostMapping
    ResponseEntity<Void> save(@Valid @RequestBody NewUserRequest newUserRequest) {
        UserController.log.info("{} - save - message: init save new user workflow, user.email: {} <---- HTTP/BEGIN (POST)", getClass().getName(), newUserRequest.email());
        NewUser newUser = NewUserRequestMapper.toDomain(newUserRequest);
        this.saveUserInputPort.save(newUser);

        UserController.log.info("{} - save - message: end save new user workflow, user.email: {}, http.status: {} ----> HTTP/END (POST)", getClass().getName(), newUserRequest.email(), HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
