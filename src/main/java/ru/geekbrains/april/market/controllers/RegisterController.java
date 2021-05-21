package ru.geekbrains.april.market.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.april.market.dtos.UserDto;
import ru.geekbrains.april.market.models.User;
import ru.geekbrains.april.market.services.RegisterService;

@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
@Slf4j
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<?> register(@RequestParam String login, @RequestParam String password, @RequestParam String email)
    {
        User newUser = registerService.register(login, password, email);
        return new ResponseEntity<>(new UserDto(newUser.getUsername(), newUser.getEmail()), HttpStatus.CREATED);
    }
}
