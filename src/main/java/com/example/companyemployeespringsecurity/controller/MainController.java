package com.example.companyemployeespringsecurity.controller;


import com.example.companyemployeespringsecurity.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class MainController {

    @RequestMapping("/")
    public String main(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        modelMap.addAttribute("isLoggedIn", currentUser != null);

        return "main";
    }
}
