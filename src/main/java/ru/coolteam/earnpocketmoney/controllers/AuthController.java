package ru.coolteam.earnpocketmoney.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.coolteam.earnpocketmoney.authorization.AuthRequest;
import ru.coolteam.earnpocketmoney.authorization.AuthResponse;
import ru.coolteam.earnpocketmoney.authorization.RegistrationRequest;
import ru.coolteam.earnpocketmoney.authorization.jwt.JwtProvider;
import ru.coolteam.earnpocketmoney.models.Child;
import ru.coolteam.earnpocketmoney.models.Parent;
import ru.coolteam.earnpocketmoney.models.UserEntity;
import ru.coolteam.earnpocketmoney.repositories.RoleRepository;
import ru.coolteam.earnpocketmoney.services.UserService;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
       // UserEntity u = new UserEntity();
        if(registrationRequest.getRole().equals("ROLE_PARENT")){
            Parent parent = new Parent();
            parent.setLogin(registrationRequest.getLogin());
            parent.setPassword(registrationRequest.getPassword());
            parent.setRole(roleRepository.findByRole(registrationRequest.getRole()));
            userService.saveUser(parent);
            return "OK";
        }else {
            Child child = new Child();
            child.setLogin(registrationRequest.getLogin());
            child.setPassword(registrationRequest.getPassword());
            child.setRole(roleRepository.findByRole(registrationRequest.getRole()));
            userService.saveUser(child);
            return "OK";
        }

        /*u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        userService.saveUser(u);
        return "OK";*/
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new AuthResponse(token);
    }
}
