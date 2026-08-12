package com.example.demo.service;

import com.example.demo.dto.UserCreateRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> getUsers(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);

        Page<UserResponse> response = users.map(user ->
                new UserResponse(user.getId(),user.getName(),user.getAge(),user.getEmail()));
        return response;
    }

    @Transactional
    public UserResponse addUser(UserCreateRequest request) {

        User user = new User();

        user.setAge(request.getAge());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved = repository.save(user);

        UserResponse response = new UserResponse(saved.getId(), saved.getName(), saved.getAge(),saved.getEmail());

        return response;
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        UserResponse response = new UserResponse(user.getId(), user.getName(), user.getAge(),user.getEmail());

        return response;
    }

    @Transactional
    public UserResponse updateUser(Long id, UserCreateRequest userCreate) {

        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        user.setName(userCreate.getName());
        user.setAge(userCreate.getAge());
        user.setEmail(userCreate.getEmail());

        User saved = repository.save(user);

        UserResponse response = new UserResponse(saved.getId(), saved.getName(), saved.getAge(),saved.getEmail());

        return response;
    }

    @Transactional
    public void deleteUser(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new UserNotFoundException(id);
    }
}