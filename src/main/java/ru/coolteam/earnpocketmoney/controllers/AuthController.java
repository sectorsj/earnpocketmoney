package ru.coolteam.earnpocketmoney.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.coolteam.earnpocketmoney.authorization.AuthRequest;
import ru.coolteam.earnpocketmoney.authorization.AuthResponse;
import ru.coolteam.earnpocketmoney.authorization.RegistrationRequest;
import ru.coolteam.earnpocketmoney.authorization.jwt.JwtProvider;
import ru.coolteam.earnpocketmoney.models.*;
import ru.coolteam.earnpocketmoney.repositories.RoleRepository;
import ru.coolteam.earnpocketmoney.services.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepository;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        User u = new User();
        Role r = roleRepository.findByRole(registrationRequest.getRole());
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        u.setRole(r);
        userService.saveUser(u);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthResponse(token);
    }
}
