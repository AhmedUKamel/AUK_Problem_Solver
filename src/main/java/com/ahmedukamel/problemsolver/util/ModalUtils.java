package com.ahmedukamel.problemsolver.util;

import org.springframework.web.servlet.ModelAndView;

public class ModalUtils {
    public static void successfulRegistration(ModelAndView modelAndView) {
        modelAndView.addObject("successfulModal", true);
        modelAndView.addObject("modalTitle", "Account Registered Successfully!");
        modelAndView.addObject("modalMessage", "Check your email for account activation before login.");
    }

    public static void successfulUpdateProfile(ModelAndView modelAndView) {
        modelAndView.addObject("successfulModal", true);
        modelAndView.addObject("modalTitle", "Good job!");
        modelAndView.addObject("modalMessage", "Personal Information Updated Successfully.");
    }
}
