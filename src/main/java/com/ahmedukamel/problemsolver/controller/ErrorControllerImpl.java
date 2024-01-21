package com.ahmedukamel.problemsolver.controller;

import com.ahmedukamel.problemsolver.util.ViewUtils;
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
        ModelAndView modelAndView = new ModelAndView("custom-view");
        int status = response.getStatus();
        if (status == HttpStatus.NOT_FOUND.value()) {
            ViewUtils.error404(modelAndView);
        } else if (status == HttpStatus.FORBIDDEN.value()) {
            ViewUtils.error403(modelAndView);
        } else ViewUtils.error404(modelAndView);
        return modelAndView;
    }
}
