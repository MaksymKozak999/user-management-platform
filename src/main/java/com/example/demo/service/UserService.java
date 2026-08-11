package com.example.demo.service;

import com.example.demo.dto.UserCreateRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserResponse> getUsers() {
        List<User> users = repository.findAll();

        List<UserResponse> response = users.stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge(),user.getEmail()))
                .toList();
        return response;
    }

    public UserResponse addUser(UserCreateRequest request) {

        User user = new User();

        user.setAge(request.getAge());
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        User saved = repository.save(user);

        UserResponse response = new UserResponse(saved.getId(), saved.getName(), saved.getAge(),saved.getEmail());

        return response;
    }

    public UserResponse getUserById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        UserResponse response = new UserResponse(user.getId(), user.getName(), user.getAge(),user.getEmail());

        return response;
    }

    public UserResponse updateUser(Long id, UserCreateRequest userCreate) {

        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        user.setName(userCreate.getName());
        user.setAge(userCreate.getAge());
        user.setEmail(userCreate.getEmail());

        User saved = repository.save(user);

        UserResponse response = new UserResponse(saved.getId(), saved.getName(), saved.getAge(),saved.getEmail());

        return response;
    }

    public void deleteUser(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new UserNotFoundException(id);
    }
}