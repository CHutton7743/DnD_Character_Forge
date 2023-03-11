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

    @PostMapping("/api/v1/login/submit")
    public String login(LoginRequest loginRequest, BindingResult bindingResult, HttpSession session) {
        String admin = "admin@localhost";
        if (loginRequest.getEmail().equals(admin) && loginRequest.getPassword().equals("admin")) {
            // Authentication succeeded, create session
            session.setAttribute("userDetails", userService.loadUserByUsername(loginRequest.getEmail()));
            session.setAttribute("isAdmin", true);
            return "redirect:/api/v1/home";
        }
        // Authenticate the user
        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmail());
        if (null != userDetails || !userService.checkIfPasswordMatches(userDetails, loginRequest.getPassword())) {
            // authentication failed, redirect back to login page with error message
            return "redirect:/api/v1/login";
        }

        // Authentication succeeded, create session
        session.setAttribute("userDetails", userDetails);
        session.setAttribute("isAdmin", userDetails.getUsername().equals(admin));

        return "redirect:/api/v1/home";
    }

    @GetMapping("/api/v1/home")
    public String home(HttpSession session) {
        // Get the user details from the session
        UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");

        if (userDetails == null) {
            // If user is not authenticated, redirect back to login page
            return "redirect:/api/v1/login";
        }
        return "index";
    }

}
