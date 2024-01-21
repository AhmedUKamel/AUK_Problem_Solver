package com.ahmedukamel.problemsolver.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AlreadyLoggedInException.class)
    public ModelAndView handleAlreadyLoggedInException() {
        return new ModelAndView("redirect:/dashboard");
    }
}
