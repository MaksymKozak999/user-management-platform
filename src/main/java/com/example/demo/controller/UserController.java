    package com.example.demo.controller;

    import com.example.demo.dto.UserCreateRequest;
    import com.example.demo.dto.UserResponse;
    import com.example.demo.service.UserService;
    import jakarta.validation.Valid;
    import org.springframework.data.domain.Page;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;



    @RestController
    @RequestMapping("/users")
    public class UserController {

        private final UserService userService;

        public UserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping
        public Page<UserResponse> getUsers(Pageable pageable){
            return userService.getUsers(pageable);
        }

        @GetMapping("/{id}")
        public UserResponse getUser(@PathVariable Long id) {
            return userService.getUserById(id);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
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