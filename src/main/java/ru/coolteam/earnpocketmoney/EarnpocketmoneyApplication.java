package ru.coolteam.earnpocketmoney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
public class EarnpocketmoneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EarnpocketmoneyApplication.class, args);
	}

}
//
//// небольшой костыль для таймлифа
//@Controller
//class IndexController {
//
////	@RequestMapping(value = "/", method = RequestMethod.GET)
////	public String index(){
////		return "index";
////	}
//
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String index(Model model){
//		model.addAttribute("message", "Мы рады приветствовать Вас!");
//		return "index";
//	}
//}