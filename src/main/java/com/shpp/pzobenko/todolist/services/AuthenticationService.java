package com.shpp.pzobenko.todolist.services;

import com.shpp.pzobenko.todolist.repositorys.UserRepository;
import com.shpp.pzobenko.todolist.usersinformation.UserOfToDo;
import com.shpp.pzobenko.todolist.auth.AuthenticationRequest;
import com.shpp.pzobenko.todolist.auth.AuthenticationResponse;
import com.shpp.pzobenko.todolist.auth.RegisterRequest;
import com.shpp.pzobenko.todolist.config.JwtService;
import com.shpp.pzobenko.todolist.usersinformation.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService service;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        UserOfToDo user = UserOfToDo.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void admin() {
        UserOfToDo user = UserOfToDo.builder()
                .username("Admin")
                .password(passwordEncoder.encode("admin1234"))
                .role(Role.ADMIN)
                .build();
        repository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserOfToDo user = repository.findById(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(
                                "User with username " + request.getUsername() + " not found"
                        )
                );
        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
