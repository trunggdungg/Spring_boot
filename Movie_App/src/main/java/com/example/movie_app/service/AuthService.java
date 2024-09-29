package com.example.movie_app.service;

import com.example.movie_app.entity.User;
import com.example.movie_app.model.User_Role;
import com.example.movie_app.model.request.LoginRequest;
import com.example.movie_app.model.request.SignupRequest;
import com.example.movie_app.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession httpSession;
    public void login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        // Lưu thông tin user vào session
        //hoac co the luu trong cookie,redis,db,...
        httpSession.setAttribute("CURRENT_USER", user);
    }

    public void logout() {
        httpSession.removeAttribute("CURRENT_USER");

    }

    public void signup(SignupRequest signupRequest) {
        User user = User.builder()
            .name(signupRequest.getName())
            .email(signupRequest.getEmail())
            .password(passwordEncoder.encode(signupRequest.getPassword()))
            .role(User_Role.USER)
            .createdAt(LocalDateTime.now())
            .build();
        userRepository.save(user);
    }
}
