package ru.coolteam.earnpocketmoney.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
