package com.DnDForge.Crucible.Forge.Registration;

import com.DnDForge.Crucible.Forge.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/api/v1/registration")
    public String register() {
        return "registration";
    }

    @PostMapping("/api/v1/registration")
    public String registerNewUser(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "/api/v1/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
