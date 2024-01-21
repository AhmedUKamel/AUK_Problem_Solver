package com.ahmedukamel.problemsolver.controller;

import com.ahmedukamel.problemsolver.dto.CheckAccountRequest;
import com.ahmedukamel.problemsolver.dto.LoginRequest;
import com.ahmedukamel.problemsolver.dto.RegisterRequest;
import com.ahmedukamel.problemsolver.exception.AlreadyLoggedInException;
import com.ahmedukamel.problemsolver.model.Gender;
import com.ahmedukamel.problemsolver.model.User;
import com.ahmedukamel.problemsolver.service.AccountVerificationService;
import com.ahmedukamel.problemsolver.service.AuthService;
import com.ahmedukamel.problemsolver.service.UserService;
import com.ahmedukamel.problemsolver.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final AuthService authService;
    private final AccountVerificationService accountVerificationService;

    @GetMapping("check")
    public ModelAndView getCheckAccountPage(@AuthenticationPrincipal User principal, @ModelAttribute("status") String status) {
        if (principal != null) {
            throw new AlreadyLoggedInException();
        }
        ModelAndView modelAndView = new ModelAndView("account-check");
        modelAndView.addObject("user", new CheckAccountRequest());
        if (status.equals(ModalType.successfulRegistration.name())) {
            ModalUtils.successfulRegistration(modelAndView);
        }
        return modelAndView;
    }

    @PostMapping("check")
    public ModelAndView checkAccount(@AuthenticationPrincipal User principal, @Validated @ModelAttribute("user") CheckAccountRequest user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (principal != null) {
            throw new AlreadyLoggedInException();
        }
        if (result.hasErrors()) {
            return new ModelAndView("account-check");
        }
        redirectAttributes.addFlashAttribute("email", user.getEmail());
        if (userService.isEmailExists(user.getEmail())) {
            return new ModelAndView("redirect:/accounts/login");
        } else {
            return new ModelAndView("redirect:/accounts/register");
        }
    }

    @GetMapping("login")
    public ModelAndView getLoginPage(@AuthenticationPrincipal User principal, @ModelAttribute("email") String email) {
        if (principal != null) {
            throw new AlreadyLoggedInException();
        }
        ModelAndView modelAndView = new ModelAndView("account-login");
        modelAndView.addObject("user", new LoginRequest(email));
        return modelAndView;
    }

    @PostMapping("login")
    public ModelAndView login(@AuthenticationPrincipal User principal, @Validated @ModelAttribute("user") LoginRequest user, BindingResult result) {
        if (principal != null) {
            throw new AlreadyLoggedInException();
        }
        if (result.hasErrors()) {
            return new ModelAndView("account-login");
        }
        authService.login(user);
        return new ModelAndView("redirect:/dashboard");
    }

    @GetMapping("register")
    public ModelAndView getRegisterPage(@AuthenticationPrincipal User principal, @ModelAttribute("email") String email) {
        if (principal != null) {
            throw new AlreadyLoggedInException();
        }
        // TODO: Prevent Access without email
        ModelAndView modelAndView = new ModelAndView("account-register-1");
        modelAndView.addObject("user", RegisterRequest.builder().email(email).build());
        return modelAndView;
    }

    @PostMapping("register")
    public ModelAndView register(@AuthenticationPrincipal User principal, @Validated @ModelAttribute("user") RegisterRequest user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (principal != null) {
            throw new AlreadyLoggedInException();
        }
        if (user.getPassword() == null || result.hasFieldErrors("password") || result.hasFieldErrors("confirm") || result.hasFieldErrors("name")) {
            return new ModelAndView("account-register-1");
        }
        if (user.getUsername() == null || result.hasFieldErrors("username") || result.hasFieldErrors("phone") || result.hasFieldErrors("title")) {
            ModelAndView modelAndView = new ModelAndView("account-register-2");
            modelAndView.addObject("genders", List.of(Gender.MALE, Gender.FEMALE));
            return modelAndView;
        }
        if (result.hasFieldErrors("bio")) {
            return new ModelAndView("account-register-3");
        }
        authService.register(user);
        redirectAttributes.addFlashAttribute("status", ModalType.successfulRegistration.name());
        return new ModelAndView("redirect:/accounts/check");
    }

    @GetMapping("verify/{token}")
    public ModelAndView getVerificationPage(@PathVariable("token") String token) {
        ModelAndView modelAndView = new ModelAndView("custom-view");
        TokenStatus status = accountVerificationService.verifyToken(token);
        if (status.equals(TokenStatus.ACCEPTED)) {
            ViewUtils.acceptedActivation(modelAndView);
        } else if (status.equals(TokenStatus.USED)) {
            ViewUtils.usedActivation(modelAndView);
        } else if (status.equals(TokenStatus.INVALID)) {
            ViewUtils.invalidActivation(modelAndView);
        }
        return modelAndView;
    }
}
