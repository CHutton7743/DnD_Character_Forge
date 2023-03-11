package com.DnDForge.Crucible.Forge.APIs.APIComponents;

import com.DnDForge.Crucible.Forge.Email.EmailSender;
import com.DnDForge.Crucible.Forge.Email.EmailService;
import com.DnDForge.Crucible.Forge.Entity.AppUser;
import com.DnDForge.Crucible.Forge.Entity.ConfirmationToken;
import com.DnDForge.Crucible.Forge.Registration.EmailValidator;
import com.DnDForge.Crucible.Forge.Registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Scanner;

@Service
@AllArgsConstructor
@Transactional
public class RegistrationService {
    @Autowired
    private final ConfirmationTokenService confirmationTokenService;
    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private final UserService userService;
    @Autowired
    private final EmailService emailService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        String token = userService.signUpUser(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword()
        ));

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        emailService.send(request.getEmail(), buildEmail(request.getFirstName(), link, token));
        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        userService.enableAppUser(confirmationToken.getUser().getEmail());
        return "confirmed";
    }

    private String buildEmail(String name, String link, String token) {
        File file = new File("src/main/resources/templates/ConfirmEmail.html");
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder html = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.replace("NAME_GOES_HERE", name);
                line = line.replace("LINK_GOES_HERE", link);
                line = line.replace("TOKEN_VALUE", token);
                html.append(line);
            }
            scanner.close();
            return html.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
