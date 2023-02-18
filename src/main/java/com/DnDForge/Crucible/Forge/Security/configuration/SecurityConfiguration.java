package com.DnDForge.Crucible.Forge.Security.configuration;

import com.DnDForge.Crucible.Forge.Security.PasswordEncoder;
import com.DnDForge.Crucible.Forge.Entity.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.File;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserService userService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                    .requestMatchers("/api/v*/registration/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                .formLogin()
                    .loginPage("/api/v1/login")
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("/api/v1/logout")
                    .logoutSuccessUrl("/api/v1/login")
                    .permitAll()
                    .deleteCookies("JSESSIONID");
        return http.build();
    }


    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());

    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder.bCryptPasswordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

}

