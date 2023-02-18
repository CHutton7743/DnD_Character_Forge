package com.DnDForge.Crucible.Forge.Login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/api/v1/login")
    public String login() {
        return "login";
    }

    @PostMapping("/api/v1/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Perform authentication and create the login session
        return "redirect:/index";
    }

    @GetMapping("/api/v1/logout")
    public String logout() {
        // Perform logout and destroy the login session
        return "redirect:/logout";
    }
}
