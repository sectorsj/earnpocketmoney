package ru.coolteam.earnpocketmoney.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.coolteam.earnpocketmoney.authorization.AuthRequest;
import ru.coolteam.earnpocketmoney.authorization.JwtResponse;
import ru.coolteam.earnpocketmoney.authorization.jwt.JwtProvider;
import ru.coolteam.earnpocketmoney.models.*;
import ru.coolteam.earnpocketmoney.repositories.RoleRepository;
import ru.coolteam.earnpocketmoney.services.UserService;

//@RestController
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepository;

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;


//    @PostMapping("/register")
//    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
//        User u = new User();
//        Role r = roleRepository.findByRole(registrationRequest.getRole());
//        u.setPassword(registrationRequest.getPassword());
//        u.setLogin(registrationRequest.getLogin());
//        u.setRole(r);
//        userService.saveUser(u);
//        return "OK";
//    }

//   @PostMapping("/register")
//    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest, Model model) {
//        User u = new User();
//        Role r = roleRepository.findByRole(registrationRequest.getRole());
//        u.setPassword(registrationRequest.getPassword());
//        u.setLogin(registrationRequest.getLogin());
//        u.setRole(r);
//        userService.saveUser(u);
//        return "registration";
//    }

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";

    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userForm.setRole(roleRepository.findByRole("ROLE_PARENT"));
        userService.saveUser(userForm);
//        return "redirect:/api/v1/tasks/all";
        return "redirect:/api/v1/cabinet";
    }

//    @PostMapping("/auth")
//    public ResponseEntity<?> auth(@RequestBody AuthRequest request) {
//        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
//        String token = jwtProvider.generateToken(user.getLogin());
//        return ResponseEntity.ok(new JwtResponse(token));
//    }



    @GetMapping("/auth")
    public String authentication(Model model) {
        model.addAttribute("userForm", new User());
        return "login";
    }

/*    @GetMapping()
    public ResponseEntity<?> authentication(@RequestBody AuthRequest request) {
        User user = userService.findByLogin(request.getLogin());
        String token = jwtProvider.generateToken(user.getLogin());
        return ResponseEntity.ok(new JwtResponse(token));
    }*/

    @PostMapping("/auth")
    public String authentication(@ModelAttribute("userForm") User userForm) {

        /*userForm.setRole(roleRepository.findByRole("ROLE_PARENT"));
        userService.findByLogin("parent1");*/

        UserDetails userDetails = userDetailsService.loadUserByUsername(userForm.getLogin());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userForm.getPassword(), userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            ////проверка авторизации
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }

            System.out.println(principal.getClass());
            System.out.println(username);
            ////
        }


        return "redirect:/api/v1/tasks/cabinet";
    }
}
