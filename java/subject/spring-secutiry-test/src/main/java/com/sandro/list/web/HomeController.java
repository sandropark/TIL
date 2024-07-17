package com.sandro.list.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping
    public String home() {
        return "index";
    }

    @RequestMapping("/login")
    public String login(boolean error, Model model) {
        if (error) {
            model.addAttribute("errorMessage", "유효하지 않은 정보입니다.");
        }
        return "login";
    }

}
