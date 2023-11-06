package ru.aptech.bustrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.aptech.bustrack.services.UserService;

@Controller

public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
    @GetMapping("/reg")
    public String reg() {
        return "reg";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }


    //Этот код мы не используем, он относится к ТИМЛИФ:
//    @PostMapping("/user")
//    public ModelAndView createUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
//        ModelAndView modelAndView = new ModelAndView();
//        try {
//            String login = userService.saveUser(user);
//            modelAndView.setViewName("redirect:/welcome"); //при редиректе надо добавлять флеш-аттрибут, ниже строчкой мы это делаем
//            redirectAttributes.addFlashAttribute("login", login);  //флеш-аттрибут
//        } catch (RuntimeException e) {
//            modelAndView.setViewName("redirect:/"); //при редиректе надо добавлять флеш-аттрибут, ниже строчкой мы это делаем
//            redirectAttributes.addFlashAttribute("err", //флеш-аттрибут
//                    String.format("Ошибка при регистрации: %s", e.getMessage()));
//        }
//        return modelAndView;
//    }
}
