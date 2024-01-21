package com.ahmedukamel.problemsolver.util;

import org.springframework.web.servlet.ModelAndView;

public class MenuUtils {
    public static void profilePage(ModelAndView modelAndView) {
        modelAndView.addObject("menuProfile", true);
        modelAndView.addObject("profileChangePassword", false);
        modelAndView.addObject("profilePersonalInformation", true);
    }

    public static void changePasswordPage(ModelAndView modelAndView) {
        modelAndView.addObject("menuProfile", true);
        modelAndView.addObject("profileChangePassword", true);
        modelAndView.addObject("profilePersonalInformation", false);
    }
}
