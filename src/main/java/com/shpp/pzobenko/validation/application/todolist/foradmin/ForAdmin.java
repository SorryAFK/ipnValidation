package com.shpp.pzobenko.validation.application.todolist.foradmin;

import com.shpp.pzobenko.validation.application.todolist.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForAdmin implements CommandLineRunner {
    private final AuthenticationService service;
    @Override
    public void run(String... args) {
        service.admin();
    }
}
