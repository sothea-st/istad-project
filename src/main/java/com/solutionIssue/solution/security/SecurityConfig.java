package com.solutionIssue.solution.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

     private final UserDetailsService userDetailsService;
     private final PasswordEncoder passwordEncoder;

     // Beand DaoAuthenticationProvider provide skill to spring security for work
     // with user from database
     @Bean
     DaoAuthenticationProvider daoAuthenticationProvider() {
          DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
          auth.setUserDetailsService(userDetailsService);// provide knowledge for retrieve data from database
          auth.setPasswordEncoder(passwordEncoder); // provide knowledge for encode password
          return auth;
     }

     // create bean securityFilterChain
     @Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

          // add security to endpoint
          // add authorizeHttpRequests
          // Endpoint security config
          http.authorizeHttpRequests(endpoint -> endpoint
                    .requestMatchers("/api/v1/auth/**").permitAll()
                    // .requestMatchers(HttpMethod.POST, "/api/v1/accounts/**").hasAnyRole("USER")
                    // .requestMatchers(HttpMethod.GET, "/api/v1/accounts/**").hasAnyRole("USER")
                    // .requestMatchers(HttpMethod.PUT, "/api/v1/accounts/**").hasAnyRole("USER")
                    // .requestMatchers(HttpMethod.PATCH, "/api/v1/accounts/**").hasAnyRole("USER")
                    // .requestMatchers(HttpMethod.DELETE, "/api/v1/accounts/**",
                    // "/api/v1/account-types/**").hasAnyRole("ADMIN")
                    // .requestMatchers(HttpMethod.POST,
                    // "/api/v1/account-types/**").hasAnyRole("MANAGER", "ADMIN")
                    // .requestMatchers(HttpMethod.GET, "/api/v1/account-types/**").hasRole("USER")
                    // .requestMatchers(HttpMethod.PUT,
                    // "/api/v1/account-types/**").hasAnyRole("MANAGER", "ADMIN")
                    // .requestMatchers(HttpMethod.PATCH,
                    // "/api/v1/account-types/**").hasAnyRole("MANAGER", "ADMIN")
                    .anyRequest().authenticated());

          // add security mechanism jwt
          /*
           * this means tell spring use jwt
           * this code will required Bean JwtDecoder
           */
          http.oauth2ResourceServer(jwt -> jwt.jwt(Customizer.withDefaults()));

          // disable token
          http.csrf(
                    token -> token.disable());

          /*
           * for REST API
           * security must be stateless session
           * make Stateless session
           * Stateless session means there no more session
           * spring will stop check token and stop store token
           */

          http.sessionManagement(
                    session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

          return http.build();
     }
}
