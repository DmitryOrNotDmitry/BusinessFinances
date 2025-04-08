package ru.dmytrium.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.dmytrium.main.repo.InvolveBusinessRepository;
import ru.dmytrium.main.services.BusinessService;
import ru.dmytrium.main.services.RoleService;

import static org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher;

@Configuration
public class SecurityConfig {

    private final static String BUSINESS_ID_NAME = "businessId";

    private final InvolveBusinessRepository involveRepository;

    private final BusinessAuthManager businessAuthManager;

    @Autowired
    public SecurityConfig(BusinessService businessService, InvolveBusinessRepository involveRepository) {
        this.businessAuthManager = new BusinessAuthManager(businessService, "businessId");
        this.involveRepository = involveRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(regexMatcher("/lab.*")).permitAll()
                        .requestMatchers("/", "/login", "/register", "/images/**", "/css/**", "/js/**").permitAll()

                        .requestMatchers("/businesses/new").authenticated()

                        .requestMatchers(HttpMethod.POST, "/businesses/{businessId}/settings",
                                "/businesses/{businessId}/delete"
                        )
                        .access(new UserHasRoleManager(involveRepository, BUSINESS_ID_NAME,
                                RoleService.ROLE_OWNER))

                        .requestMatchers(HttpMethod.POST, "/businesses/{businessId}/participant",
                                "/businesses/{businessId}/participant/delete"
                        )
                        .access(new UserHasRoleManager(involveRepository, BUSINESS_ID_NAME,
                                RoleService.ROLE_OWNER, RoleService.ROLE_ADMIN))

                        .requestMatchers(HttpMethod.POST,"/businesses/{businessId}/config/**",
                                "/businesses/{businessId}/transaction",
                                "/businesses/{businessId}/transaction/delete",
                                "/businesses/{businessId}/obligation",
                                "/businesses/{businessId}/obligation/delete"
                        )
                        .access(new UserHasRoleManager(involveRepository, BUSINESS_ID_NAME,
                                RoleService.ROLE_OWNER, RoleService.ROLE_ADMIN, RoleService.ROLE_WORKER))

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
