package com.ahmedukamel.problemsolver.util;

import org.springframework.web.servlet.ModelAndView;

public class ViewUtils {
    public static void acceptedActivation(ModelAndView modelAndView) {
        modelAndView.addObject("title", "Account Activation");
        modelAndView.addObject("viewSvg", "/dist/images/success-illustration.svg");
        modelAndView.addObject("viewHeading", "");
        modelAndView.addObject("viewTitle", "Account Activated Successfully!");
        modelAndView.addObject("viewMessage", "Congratulations, you can login to your account now.");
        modelAndView.addObject("buttonText", "Go to login");
        modelAndView.addObject("buttonRedirect", "/accounts/check");
    }

    public static void usedActivation(ModelAndView modelAndView) {
        modelAndView.addObject("title", "Account Activation");
        modelAndView.addObject("viewSvg", "/dist/images/warning-illustration.svg");
        modelAndView.addObject("viewHeading", "");
        modelAndView.addObject("viewTitle", "Expired token!");
        modelAndView.addObject("viewMessage", "Your account is already activated.");
        modelAndView.addObject("buttonText", "Go to login");
        modelAndView.addObject("buttonRedirect", "/accounts/check");
    }

    public static void invalidActivation(ModelAndView modelAndView) {
        modelAndView.addObject("title", "Account Activation");
        modelAndView.addObject("viewSvg", "/dist/images/wrong-illustration.svg");
        modelAndView.addObject("viewHeading", "");
        modelAndView.addObject("viewTitle", "Invalid token!");
        modelAndView.addObject("viewMessage", "Make sure that your token is valid.");
        modelAndView.addObject("buttonText", "Back to Home");
        modelAndView.addObject("buttonRedirect", "/");
    }

    public static void error404(ModelAndView modelAndView) {
        modelAndView.addObject("title", "Not found");
        modelAndView.addObject("viewSvg", "/dist/images/error-illustration.svg");
        modelAndView.addObject("viewHeading", "404");
        modelAndView.addObject("viewTitle", "Oops. This page has gone missing!");
        modelAndView.addObject("viewMessage", "You may have mistyped the address or the page may have moved.");
        modelAndView.addObject("buttonText", "Back to Home");
        modelAndView.addObject("buttonRedirect", "/");
    }

    public static void error403(ModelAndView modelAndView) {
        modelAndView.addObject("title", "Forbidden");
        modelAndView.addObject("viewSvg", "/dist/images/forbidden-illustration.svg");
        modelAndView.addObject("viewHeading", "403");
        modelAndView.addObject("viewTitle", "Stop. This page is authenticated!");
        modelAndView.addObject("viewMessage", "You may be not authorized to access or you need to login.");
        modelAndView.addObject("buttonText", "Go to login");
        modelAndView.addObject("buttonRedirect", "/accounts/check");
    }
}
