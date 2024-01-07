package com.ahmedukamel.problemsolver.controller;

import com.ahmedukamel.problemsolver.dto.UpdatePasswordRequest;
import com.ahmedukamel.problemsolver.dto.UpdateUserRequest;
import com.ahmedukamel.problemsolver.model.User;
import com.ahmedukamel.problemsolver.service.ProfileService;
import com.ahmedukamel.problemsolver.util.AuthenticatedUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("user/profile")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public String updateProfilePage(Model model) {
        User user = AuthenticatedUser.getAuthenticatedUser();
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("menuProfile", true);
            model.addAttribute("profilePersonalInformation", true);
            model.addAttribute("request", UpdateUserRequest.builder().name(user.getName()).title(user.getTitle()).bio(user.getBio()).picture(user.getPicture()).build());
        }
        return "user-personal-information";
    }

    @PostMapping
    public String updateProfile(@Validated @ModelAttribute("request") UpdateUserRequest request, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            profileService.updateProfile(request);
            model.addAttribute("updatedSuccessfully", true);
        }
        model.addAttribute("user", AuthenticatedUser.getAuthenticatedUser());
        model.addAttribute("menuProfile", true);
        model.addAttribute("profilePersonalInformation", true);
        return "user-personal-information";
    }

    @GetMapping("password/update")
    public String updatePasswordPage(Model model) {
        User authenticatedUser = AuthenticatedUser.getAuthenticatedUser();
        if (authenticatedUser != null) {
            model.addAttribute("user", authenticatedUser);
            model.addAttribute("menuProfile", true);
            model.addAttribute("profileChangePassword", true);
            model.addAttribute("request", UpdatePasswordRequest.builder().email(authenticatedUser.getEmail()).build());
        }
        return "user-change-password";
    }

    @PostMapping("password/update")
    public String updatePassword(@Validated @ModelAttribute("request") UpdatePasswordRequest request, BindingResult result, HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("user", AuthenticatedUser.getAuthenticatedUser());
        model.addAttribute("menuProfile", true);
        model.addAttribute("profileChangePassword", true);
        if (!result.hasErrors()) {
            profileService.updatePassword(httpServletRequest, request.getNewPassword());
            model.addAttribute("updatedSuccessfully", true);
        }
        return "user-change-password";
    }
}
