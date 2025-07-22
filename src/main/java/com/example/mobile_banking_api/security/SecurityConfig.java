package com.example.mobile_banking_api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    private final String ROLE_ADMIN = "ADMIN";
    private final String ROLE_STAFF = "STAFF";
    private final String ROLE_CUSTOMER = "CUSTOMER";

    @Bean
    public InMemoryUserDetailsManager configureUsers() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("qwer"))
                .roles(ROLE_ADMIN)
                .build();
        manager.createUser(admin);

        UserDetails staff = User.builder()
                .username("staff")
                .password(passwordEncoder.encode("qwer"))
                .roles(ROLE_STAFF)
                .build();
        manager.createUser(staff);

        UserDetails customer = User.builder()
                .username("customer")
                .password(passwordEncoder.encode("qwer"))
                .roles(ROLE_CUSTOMER)
                .build();
        manager.createUser(customer);

        return manager;
    }

    @Bean
    public SecurityFilterChain configureApiSecurity(HttpSecurity http) throws Exception {

        // Make all endpoints secured
        http.authorizeHttpRequests(endpoints -> endpoints
                .requestMatchers(HttpMethod.POST, "/api/v1/customers/**")
                .hasAnyRole(ROLE_ADMIN, ROLE_STAFF)
                .requestMatchers(HttpMethod.PUT, "/api/v1/customers/**")
                .hasAnyRole(ROLE_ADMIN, ROLE_STAFF)
                .requestMatchers(HttpMethod.DELETE, "/api/v1/customers/**")
                .hasAnyRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.GET, "/api/v1/customers/**")
                .hasAnyRole(ROLE_ADMIN, ROLE_STAFF, ROLE_CUSTOMER)
                .requestMatchers("/api/v1/accounts/**")
                .hasAnyRole(ROLE_ADMIN, ROLE_STAFF, ROLE_CUSTOMER)
                .anyRequest()
                .authenticated()
        );

        // Disable form login of web
        http.formLogin(form -> form.disable());

        // Set security mechanism = HTTP Basic Authentication => JWT, OAUTH2
        http.httpBasic(Customizer.withDefaults());

        // CSRF common protection -> CSRF token
        http.csrf(token -> token.disable());

        // Make stateless API
        http.sessionManagement(session ->
                session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ));
        return http.build();
    }
}
