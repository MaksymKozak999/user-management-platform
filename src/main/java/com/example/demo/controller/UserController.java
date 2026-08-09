    package com.example.demo.controller;

    import com.example.demo.dto.UserCreateRequest;
    import com.example.demo.dto.UserResponse;
    import com.example.demo.service.UserService;

    import jakarta.validation.Valid;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/users")
    public class UserController {


        private final UserService userService;


        public UserController(UserService userService) {
            this.userService = userService;
        }


        @GetMapping
        public List<UserResponse> getUsers() {
            return userService.getUsers();
        }


        @GetMapping("/{id}")
        public UserResponse getUser(@PathVariable Long id) {
            return userService.getUserById(id);
        }


        @PostMapping
        public UserResponse addUser(@RequestBody @Valid UserCreateRequest user) {
            return userService.addUser(user);
        }


        @PutMapping("/{id}")
        public UserResponse updateUser(
                @PathVariable Long id,
                @RequestBody @Valid UserCreateRequest user) {

            return userService.updateUser(id,user);
        }


        @DeleteMapping("/{id}")
        public void deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
        }
    }