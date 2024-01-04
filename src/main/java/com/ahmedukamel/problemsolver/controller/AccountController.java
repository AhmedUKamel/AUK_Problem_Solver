package com.ahmedukamel.problemsolver.controller;

import com.ahmedukamel.problemsolver.dto.UserRequest;
import com.ahmedukamel.problemsolver.model.Gender;
import com.ahmedukamel.problemsolver.service.AuthService;
import com.ahmedukamel.problemsolver.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("account")
public class AccountController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping
    public String getCheckAccountPage(Model model) {
        model.addAttribute("user", new UserRequest());
        return "account-check";
    }

    @PostMapping
    public String processAccount(@Validated @ModelAttribute("user") UserRequest user, BindingResult result, HttpServletRequest request, Model model) {
        // Check correction and status of the email
        if (result.hasFieldErrors("email")) {
            return "account-check";
        }

        // Check existence of the user
        if (userService.isEmailExists(user.getEmail())) {
            // Check correction of the password
            if (user.getPassword() == null || result.hasFieldErrors("password")) {
                return "account-login";
            }
            // Try to log user in and authenticate this session
            if (authService.login(request, user)) {
                return "redirect:/dashboard";
            } else {
                return "account-login";
            }
        }

        // Check existence and correction of the register 1 fields
        boolean isRegister1Nulls = user.getPassword() == null || user.getConfirm() == null || user.getName() == null;
        boolean isRegister1Errors = result.hasFieldErrors("password") || result.hasFieldErrors("confirm") || result.hasFieldErrors("name");
        if (isRegister1Nulls || isRegister1Errors) {
            return "account-register-1";
        }
        // Check existence and correction of the register 2 fields
        boolean isRegister2Nulls = user.getUsername() == null || user.getPhone() == null || user.getTitle() == null;
        boolean isRegister2Errors = result.hasFieldErrors("username") || result.hasFieldErrors("phone") || result.hasFieldErrors("title");
        if (isRegister2Nulls || isRegister2Errors) {
            model.addAttribute("genders", List.of(Gender.MALE, Gender.FEMALE));
            return "account-register-2";
        }
        // Check existence and correction of the register 3 fields
        if (user.getBio() == null || result.hasFieldErrors("bio")) {
            return "account-register-3";
        }

        // Try to register the user
        if (authService.register(user)) {
            model.addAttribute("registeredSuccessfully", true);
            model.addAttribute("user", new UserRequest());
            return "account-check";
        } else {
            return "account-register-3";
        }
    }
}
