package com.ahmedukamel.problemsolver.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorControllerImpl implements ErrorController {
    @RequestMapping("error")
    public ModelAndView handleError(HttpServletResponse response) {
        int status = response.getStatus();
        if (status == HttpStatus.NOT_FOUND.value()) {
            return new ModelAndView("error-404");
        }
        return new ModelAndView("error-404");
    }
}
