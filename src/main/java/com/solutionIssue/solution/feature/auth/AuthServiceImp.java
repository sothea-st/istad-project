package com.solutionIssue.solution.feature.auth;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.solutionIssue.solution.feature.auth.dto.AuthResponse;
import com.solutionIssue.solution.feature.auth.dto.LoginRequest;
import com.solutionIssue.solution.feature.user.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import java.util.List;
 

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
     private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;
     private final DaoAuthenticationProvider daoAuthenticationProvider;
     private final JwtEncoder accessTokenJwtEncoder;

     @Override
     public AuthResponse login(LoginRequest loginRequest) {
          // Authenticate client with username (phoneNumber) and password
          /*
           * Method UsernamePasswordAuthenticationToken will auto invoke DaoAuthenticationProvider
           */
          Authentication auth = new UsernamePasswordAuthenticationToken(loginRequest.email(),
                    loginRequest.password());
          auth = daoAuthenticationProvider.authenticate(auth);

          // Generate JWT token by JwtEncoder
          // 1. Define JwtClaimsSet (Payload) 
          Instant now = Instant.now();
          JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                    .id(auth.getName())
                    .subject("Access APIs")
                    .issuer(auth.getName())
                    .issuedAt(now)
                    .expiresAt(now.plus(30, ChronoUnit.MINUTES))
                    .audience(List.of("NextJS", "Android", "iOS"))
                    .claim("isAdmin", true)
                    .claim("studentId", "ISTAD0010")
                    .build();

          // 2. Generate token
          String accessToken = accessTokenJwtEncoder
                    .encode(JwtEncoderParameters.from(jwtClaimsSet))
                    .getTokenValue();
       

          return AuthResponse.builder()
                    .tokenType("Bearer")
                    .accessToken(accessToken)
                    .build();
     }

}
