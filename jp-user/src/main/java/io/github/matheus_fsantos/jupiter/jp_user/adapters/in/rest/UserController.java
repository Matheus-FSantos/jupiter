package io.github.matheus_fsantos.jupiter.jp_user.adapters.in.rest;

import io.github.matheus_fsantos.jupiter.jp_user.adapters.in.rest.mapper.NewUserRequestMapper;
import io.github.matheus_fsantos.jupiter.jp_user.adapters.in.rest.request.NewUserRequest;
import io.github.matheus_fsantos.jupiter.jp_user.application.core.model.request.NewUser;
import io.github.matheus_fsantos.jupiter.jp_user.application.core.model.response.User;
import io.github.matheus_fsantos.jupiter.jp_user.application.ports.in.FindAllUsersInputPort;
import io.github.matheus_fsantos.jupiter.jp_user.application.ports.in.SaveUserInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UserController {
    private final FindAllUsersInputPort findAllUsersInputPort;
    private final SaveUserInputPort saveUserInputPort;

    @GetMapping("/ping")
    ResponseEntity<String> ping() {
        return ResponseEntity.status(HttpStatus.OK).body("pong");
    }

    @GetMapping("/find")
    ResponseEntity<List<User>> findAllUsers() {
        UserController.log.info("{} - findAllUsers - message: init find all method workflow to get all users <---- HTTP/BEGIN (POST)", UserController.class.getSimpleName());
        List<User> users = this.findAllUsersInputPort.find();

        UserController.log.info("{} - findAllUsers - message: founded {} users in application - end workflow, status: {}  ----> HTTP/BEGIN (POST)", UserController.class.getSimpleName(), users.size(), HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping
    ResponseEntity<Void> save(@Valid @RequestBody NewUserRequest newUserRequest) {
        UserController.log.info("{} - save - message: init save new user workflow, user.email: {} <---- HTTP/BEGIN (POST)", getClass().getName(), newUserRequest.email());
        NewUser newUser = NewUserRequestMapper.toDomain(newUserRequest);
        this.saveUserInputPort.save(newUser);

        UserController.log.info("{} - save - message: end save new user workflow, user.email: {}, http.status: {} ----> HTTP/END (POST)", getClass().getName(), newUserRequest.email(), HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
