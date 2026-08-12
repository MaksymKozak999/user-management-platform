package com.example.demo;

import com.example.demo.dto.UserCreateRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock // Creates 'fake' repository
    private UserRepository repository;

    @InjectMocks  // Creates real userService and inserts fake repository there
    private UserService userService;

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // 1. Arrange — подготовка данных
        User user = new User("John", 25, "john@gmail.com");
        when(repository.findById(1L)).thenReturn(Optional.of(user));

        // 2. Act — вызов метода
        UserResponse response = userService.getUserById(1L);

        // 3. Assert — проверка результата
        assertEquals("John", response.getName());
        assertEquals(25, response.getAge());
        assertEquals("john@gmail.com", response.getEmail());
    }
    @Test
    void getUserById_shouldReturnUserNotFoundException_whenUserNotExist(){

        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(999L));
    }

    @Test
    void addUser_shouldReturn_NewUser(){
        UserCreateRequest request = new UserCreateRequest("Test",15,"test@gmail.com");

        User user = new User();
        user.setName("Test");
        user.setAge(15);
        user.setEmail("test@gmail.com");

        when(repository.save(any(User.class))).thenReturn(user);

        UserResponse result = userService.addUser(request);

        assertEquals("Test", result.getName());
        assertEquals(15, result.getAge());
        assertEquals("test@gmail.com", result.getEmail());
    }

}
