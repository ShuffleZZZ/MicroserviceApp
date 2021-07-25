package com.shufflezzz.ApiGateway.controller;

import com.shufflezzz.Connector.config.RabbitMQ;
import com.shufflezzz.Connector.models.dto.UpdateUserDto;
import com.shufflezzz.Connector.models.dto.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UsersController extends AbstractController {

    @GetMapping("/{id}")
    public String getUserById(@PathVariable(value = "id") Long userId) {
        return handleRequest(RabbitMQ.USER_GET_QUEUE, userId);
    }

    @PostMapping("/")
    public String createUser(@RequestBody UserDto user) {
        return handleRequest(RabbitMQ.USER_CREATE_QUEUE, user);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable(value = "id") Long userId, @RequestBody UserDto newUser) {
        return handleRequest(RabbitMQ.USER_UPDATE_QUEUE, new UpdateUserDto(userId, newUser));
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(value = "id") Long userId) {
        return handleRequest(RabbitMQ.USER_DELETE_QUEUE, userId);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto user) {
        return handleRequest(RabbitMQ.USER_LOGIN_QUEUE, user);
    }
}
