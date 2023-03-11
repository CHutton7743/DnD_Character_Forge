package com.DnDForge.Crucible.Forge.APIs;

import com.DnDForge.Crucible.Forge.Registration.RegistrationRequest;
import com.DnDForge.Crucible.Forge.APIs.APIComponents.RegistrationService;
import com.DnDForge.Crucible.Forge.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.Thymeleaf;


@AllArgsConstructor
@Controller
public class RegistrationAPI {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/api/v1/registration")
    public String register() {
        return "registration";
    }

    @PostMapping("/api/v1/registration")
    public String registerNewUser(@RequestBody RegistrationRequest request, Model model) {
        String token = registrationService.register(request);
        model.addAttribute("token", token);
        return "redirect:/api/v1/login";
    }

    @GetMapping("/api/v1/registration/confirm")
    public String confirm(@RequestParam("token") String token) {
        String status = registrationService.confirmToken(token);
        return "redirect:/api/v1/login";
    }
}
