package com.shufflezzz.SecurityManager.listener;

import com.google.gson.Gson;
import com.shufflezzz.Connector.config.RabbitMQ;
import com.shufflezzz.Connector.models.dto.UpdateUserDto;
import com.shufflezzz.Connector.models.dto.UserDto;
import com.shufflezzz.SecurityManager.models.User;
import com.shufflezzz.SecurityManager.service.UserService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserListener {

    @Autowired
    private UserService userService;

    private final Gson gson = new Gson();

    @RabbitListener(queues = RabbitMQ.USER_GET_QUEUE)
    public String handleGet(Message message) {
        final Long userId = gson.fromJson(new String(message.getBody()), Long.class);
        final Optional<User> userEntity = userService.findById(userId);

        return gson.toJson(userEntity.map(user -> userService.userToDto(user)).orElse(null));
    }

    @RabbitListener(queues = RabbitMQ.USER_CREATE_QUEUE)
    public String handleCreate(Message message) {
        final UserDto userDto = gson.fromJson(new String(message.getBody()), UserDto.class);
        return gson.toJson(userService.userToDto(userService.save(userService.dtoToUser(userDto))));
    }

    @RabbitListener(queues = RabbitMQ.USER_UPDATE_QUEUE)
    public String handleUpdate(Message message) {
        final UpdateUserDto updateUserDto = gson.fromJson(new String(message.getBody()), UpdateUserDto.class);
        final Optional<User> userEntity = userService.findById(updateUserDto.getUserId());

        return gson.toJson(userEntity.map(user ->
                userService.userToDto(userService.save(userService.updateUser(user, updateUserDto.getUserDto())))
        ).orElse(null));
    }

    @RabbitListener(queues = RabbitMQ.USER_DELETE_QUEUE)
    public String handleDelete(Message message) {
        final Long userId = gson.fromJson(new String(message.getBody()), Long.class);
        final Optional<User> userEntity = userService.findById(userId);

        if (userEntity.isPresent()) {
            final User user = userEntity.get();
            userService.delete(user);

            return gson.toJson(userService.userToDto(user));
        }

        return gson.toJson(null);
    }

    @RabbitListener(queues = RabbitMQ.USER_LOGIN_QUEUE)
    public String handleLogin(Message message) {
        final UserDto userDto = gson.fromJson(new String(message.getBody()), UserDto.class);

        // TODO.

        return gson.toJson(null);
    }
}
