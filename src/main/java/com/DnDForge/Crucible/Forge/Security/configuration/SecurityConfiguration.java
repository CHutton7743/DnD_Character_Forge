package com.DnDForge.Crucible.Forge.Security.configuration;

import com.DnDForge.Crucible.Forge.APIs.APIComponents.UserService;
import com.DnDForge.Crucible.Forge.Security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;


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
                .authorizeHttpRequests((authorize) -> authorize
                        //login and registration endpoints
                        .requestMatchers("/api/v*/registration/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/v1/login/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/confirm/**").permitAll()

                        // character endpoints
                        .requestMatchers(HttpMethod.GET, "api/v1/characters/adminGetAll").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "api/v1/characters").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or @characterSecurityService.canAccessCharacter(authentication,#id)"))
                        .requestMatchers(HttpMethod.POST, "/api/v1/characters/**").access(new WebExpressionAuthorizationManager("hasRole('USER') or hasRole('ADMIN')"))
                        .requestMatchers(HttpMethod.PUT, "/api/v1/characters/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or @characterSecurityService.canAccessCharacter(authentication,#id)"))
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/characters/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or @characterSecurityService.canAccessCharacter(authentication,#id)"))

                        // user endpoints
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasRole("ADMIN"))
                .formLogin(form -> form
                        .loginPage("/api/v1/login")
                        .loginProcessingUrl("/api/v1/login")
                        .defaultSuccessUrl("/api/v1/home", true)
                        .failureUrl("/api/v1/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/api/v1/logout")
                        .logoutSuccessUrl("/api/v1/login")
                        .permitAll());
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

