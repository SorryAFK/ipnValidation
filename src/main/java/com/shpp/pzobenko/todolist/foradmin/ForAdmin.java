package com.shpp.pzobenko.todolist.foradmin;

import com.shpp.pzobenko.todolist.services.AuthenticationService;
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
