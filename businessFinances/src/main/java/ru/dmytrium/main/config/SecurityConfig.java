package ru.dmytrium.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.dmytrium.main.services.BusinessService;

@Configuration
public class SecurityConfig {

    private final BusinessService businessService;

    private final BusinessAuthManager businessAuthManager;

    @Autowired
    public SecurityConfig(BusinessService businessService) {
        this.businessService = businessService;
        this.businessAuthManager = new BusinessAuthManager(businessService, "businessId");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/images/**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/businesses/{businessId}", "/businesses/{businessId}/**")
                            .access(businessAuthManager)
                        .anyRequest().authenticated()
                )
                .formLogin(httpSec -> httpSec
                        .loginPage("/login")
                        .defaultSuccessUrl("/businesses", false)
                )
                .logout(httpSec -> httpSec
                        .logoutUrl("/logout")
                )
                .csrf(csrt -> csrt
                        .disable()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
