package com.DnDForge.Crucible.Forge.APIs;

import ch.qos.logback.core.model.Model;
import com.DnDForge.Crucible.Forge.APIs.APIComponents.UserService;
import com.DnDForge.Crucible.Forge.APIs.Requests.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginAPI {
    @Autowired
    private UserService userService;

    @GetMapping("/api/v1/login")
    public String login() {
        return "login";
    }

    @GetMapping("/api/v1/home")
    public String home() {
        return "index";
    }

    @PostMapping("/api/v1/login/submit")
    public String login(@Validated LoginRequest loginRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid credentials");
            return "login";
        }
        // Get the user details from the database
        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmail());

        // Check if the password is correct
        if (userService.checkIfPasswordMatches(userDetails, loginRequest.getPassword())) {
            // If the password is correct, add the user details to the session
            session.setAttribute("userDetails", userDetails);
            return "index";
        } else {
            // If the password is incorrect, redirect back to the login page
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid credentials");
            return "login";
        }
    }

}
