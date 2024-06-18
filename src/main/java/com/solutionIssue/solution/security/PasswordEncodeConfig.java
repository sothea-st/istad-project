package com.solutionIssue.solution.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class PasswordEncodeConfig {
     @Bean
     PasswordEncoder passwordEncoder(){
          return new BCryptPasswordEncoder();
     }
}
