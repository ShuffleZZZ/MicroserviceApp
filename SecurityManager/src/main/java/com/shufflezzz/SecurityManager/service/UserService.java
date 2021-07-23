package com.shufflezzz.SecurityManager.service;

import com.shufflezzz.Connector.models.dto.UserDto;
import com.shufflezzz.SecurityManager.models.User;
import com.shufflezzz.SecurityManager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User dtoToUser(UserDto userDto) {
        return new User(userDto.getName(), userDto.getLogin(), userDto.getPassword(), userDto.getRole());
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user, UserDto userDto) {
        user.setName(userDto.getName());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        return user;
    }

    public UserDto userToDto(User user) {
        return new UserDto(user.getName(), user.getLogin(), user.getPassword(), user.getRole());
    }
}
