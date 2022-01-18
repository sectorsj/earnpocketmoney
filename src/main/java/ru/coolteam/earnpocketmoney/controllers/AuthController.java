package ru.coolteam.earnpocketmoney.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.coolteam.earnpocketmoney.authorization.CustomUserDetails;
import ru.coolteam.earnpocketmoney.authorization.CustomUserDetailsService;
import ru.coolteam.earnpocketmoney.authorization.jwt.JwtProvider;
import ru.coolteam.earnpocketmoney.dtos.UserInfo;
import ru.coolteam.earnpocketmoney.models.PeopleGroups;
import ru.coolteam.earnpocketmoney.models.Role;
import ru.coolteam.earnpocketmoney.models.User;
import ru.coolteam.earnpocketmoney.models.Wallet;
import ru.coolteam.earnpocketmoney.repositories.RoleRepository;
import ru.coolteam.earnpocketmoney.services.PeopleGroupsService;
import ru.coolteam.earnpocketmoney.services.UserService;
import ru.coolteam.earnpocketmoney.services.WalletService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepository;
    private final WalletService walletService;
    private final PeopleGroupsService peopleGroupsService;

    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserInfo());
        return "registration";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("userForm") UserInfo userForm,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        User user = new User();
        Wallet wallet = new Wallet(12L);
        walletService.saveWallet(wallet);
        PeopleGroups peopleGroups = peopleGroupsService.findByName(userForm.getPeopleGroupName());
        if(peopleGroups==null){
            peopleGroups = peopleGroupsService.savePeopleGroups(new PeopleGroups(userForm.getPeopleGroupName()));
        }else {
            user.setPeopleGroups(peopleGroups);
        }

        Role role = roleRepository.findByRole(userForm.getRole());
        user.setLogin(userForm.getLogin());
        user.setPassword(userForm.getPassword());
        user.setPeopleGroups(peopleGroups);
        user.setRole(role);
        user.setWallet(wallet);
        userService.saveUser(user);

        CustomUserDetails userDetails = CustomUserDetails.fromUserEntityToCustomUserDetails(user);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                userForm.getPassword(),
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //TODO необходимо изучить класс authenticationManager да и секьюрити в целом, весь моск вынес мне вчера..
      //  authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        return "redirect:/api/v1/cabinet";
    }

    @GetMapping("/auth")
    public String authentication(Model model) {
        model.addAttribute("userForm", new User());
        return "login";
    }


    @PostMapping("/auth")
    public String authentication(@ModelAttribute("userForm") User userForm) {

        CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(userForm.getLogin());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                userForm.getPassword(),
                userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//          Проверка авторизации
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
            System.out.println(principal.getClass());
            System.out.println(username);
        }
        return "redirect:/api/v1/cabinet";
    }
}
