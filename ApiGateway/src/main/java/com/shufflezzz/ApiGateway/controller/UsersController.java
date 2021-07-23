package com.shufflezzz.ApiGateway.controller;

import com.shufflezzz.Connector.config.RabbitMQ;
import com.shufflezzz.Connector.models.dto.UpdateUserDto;
import com.shufflezzz.Connector.models.dto.UserDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UsersController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable(value = "id") Long userId) {
        return (UserDto) rabbitTemplate.convertSendAndReceive(RabbitMQ.EXCHANGE, RabbitMQ.USER_GET_QUEUE, userId);
    }

    @PostMapping("/")
    public UserDto createUser(@RequestBody UserDto user) {
        return (UserDto) rabbitTemplate.convertSendAndReceive(RabbitMQ.EXCHANGE, RabbitMQ.USER_CREATE_QUEUE, user);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable(value = "id") Long userId, @RequestBody UserDto newUser) {
        return (UserDto) rabbitTemplate.convertSendAndReceive(
                RabbitMQ.EXCHANGE,
                RabbitMQ.USER_UPDATE_QUEUE,
                new UpdateUserDto(userId, newUser)
        );
    }

    @DeleteMapping("/{id}")
    public UserDto deleteUser(@PathVariable(value = "id") Long userId) {
        return (UserDto) rabbitTemplate.convertSendAndReceive(RabbitMQ.EXCHANGE, RabbitMQ.USER_DELETE_QUEUE, userId);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody UserDto user) {
        return (UserDto) rabbitTemplate.convertSendAndReceive(RabbitMQ.EXCHANGE, RabbitMQ.USER_LOGIN_QUEUE, user);
    }
}
