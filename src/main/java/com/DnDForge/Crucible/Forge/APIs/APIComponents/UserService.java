package com.DnDForge.Crucible.Forge.APIs.APIComponents;

import com.DnDForge.Crucible.Forge.APIs.APIComponents.ConfirmationTokenService;
import com.DnDForge.Crucible.Forge.APIs.Requests.ChangeEmailRequest;
import com.DnDForge.Crucible.Forge.APIs.Requests.ChangePasswordRequest;
import com.DnDForge.Crucible.Forge.APIs.Requests.ChangeUserNameRequest;
import com.DnDForge.Crucible.Forge.Entity.AppUser;
import com.DnDForge.Crucible.Forge.Entity.ConfirmationToken;
import com.DnDForge.Crucible.Forge.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private final ConfirmationTokenService confirmationTokenService;
    @Autowired
    private final static String USER_NOT_FOUND = "User with email %s not found";
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new
                UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(AppUser user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("This email is already in use. Please choose a valid email.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public void enableAppUser(String email) {
        AppUser user = userRepository.findByEmail(email).orElseThrow(() -> new
                UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
        user.setEnabled(true);
    }

    public boolean checkIfPasswordMatches(UserDetails user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    public boolean checkIfEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @PreAuthorize("hasAuthority('ADMIN') or #email == authentication.principal.username")
    public void changePassword(ChangePasswordRequest request) {
        AppUser user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new
                UsernameNotFoundException(String.format(USER_NOT_FOUND, request.getEmail())));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @PreAuthorize("hasAuthority('ADMIN') or #email == authentication.principal.username")
    public void changeEmail(ChangeEmailRequest request) {
        AppUser user = userRepository.findByEmail(request.getOldEmail()).orElseThrow(() -> new
                UsernameNotFoundException(String.format(USER_NOT_FOUND, request.getOldEmail())));
        user.setEmail(request.getNewEmail());
        userRepository.save(user);
    }

    @PreAuthorize("hasAuthority('ADMIN') or #email == authentication.principal.username")
    public void changeUserName(ChangeUserNameRequest request) {
        AppUser user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new
                UsernameNotFoundException(String.format(USER_NOT_FOUND, request.getEmail())));
        user.setUserName(request.getUsername());
        userRepository.save(user);
    }

}
