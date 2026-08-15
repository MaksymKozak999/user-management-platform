package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // constructor
    public AuthService(UserRepository userRepository,BCryptPasswordEncoder encoder,JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = encoder;
        this.jwtService = jwtService;
    }
    public String login(LoginRequest request) {
        // 1. найти пользователя по email — если не найден бросить исключение
        // 2. проверить пароль через passwordEncoder.matches()
        // 3. если пароль неверный — бросить исключение
        // 4. сгенерировать и вернуть токен
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return jwtService.generateToken(user.getEmail());
    }
}
