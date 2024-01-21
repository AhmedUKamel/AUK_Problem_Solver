package com.ahmedukamel.problemsolver.controller;

import com.ahmedukamel.problemsolver.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping
    public ModelAndView homePage(@AuthenticationPrincipal User user) {
        if (user != null) {
            return new ModelAndView("redirect:/user/profile");
        }
        return new ModelAndView("redirect:/accounts/check");
    }

    @GetMapping("dashboard")
    public String dashboard() {
        return "redirect:/";
    }
}
