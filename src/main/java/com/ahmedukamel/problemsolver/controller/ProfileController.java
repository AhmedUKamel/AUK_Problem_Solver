package com.ahmedukamel.problemsolver.controller;

import com.ahmedukamel.problemsolver.dto.UpdatePasswordRequest;
import com.ahmedukamel.problemsolver.dto.UpdateUserRequest;
import com.ahmedukamel.problemsolver.model.User;
import com.ahmedukamel.problemsolver.service.UserService;
import com.ahmedukamel.problemsolver.util.MenuUtils;
import com.ahmedukamel.problemsolver.util.ModalUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("user/profile")
@PreAuthorize("isAuthenticated()")
public class ProfileController {
    private final UserService userService;

    @GetMapping
    public ModelAndView getProfilePage(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("user-personal-information", Map.of("user", user));
        modelAndView.addObject("request", new UpdateUserRequest(user));
        MenuUtils.profilePage(modelAndView);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView updateProfile(@Validated @ModelAttribute("request") UpdateUserRequest request, BindingResult result, @AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("user-personal-information", Map.of("user", user));
        MenuUtils.profilePage(modelAndView);
        if (!result.hasErrors()) {
            userService.updateProfile(user, request);
            ModalUtils.successfulUpdateProfile(modelAndView);
        }
        return modelAndView;
    }

    @GetMapping("password/update")
    public ModelAndView updatePasswordPage(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("user-change-password", Map.of("user", user));
        modelAndView.addObject("request", new UpdatePasswordRequest(user));
        MenuUtils.changePasswordPage(modelAndView);
        return modelAndView;
    }

    @PostMapping("password/update")
    public ModelAndView updatePassword(@Validated @ModelAttribute("request") UpdatePasswordRequest request, BindingResult result, @AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("user-change-password", Map.of("user", user));
        MenuUtils.changePasswordPage(modelAndView);
        if (!result.hasErrors()) {
            userService.updatePassword(user, request.getNewPassword());
            ModalUtils.successfulUpdateProfile(modelAndView);
        }
        return modelAndView;
    }
}
