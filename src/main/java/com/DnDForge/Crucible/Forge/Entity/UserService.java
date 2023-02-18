package com.DnDForge.Crucible.Forge.Entity;

import com.DnDForge.Crucible.Forge.Registration.ConfirmationTokenService;
import com.DnDForge.Crucible.Forge.Repository.UserRepository;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
}
