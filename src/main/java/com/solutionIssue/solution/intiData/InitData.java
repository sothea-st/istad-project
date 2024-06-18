package com.solutionIssue.solution.intiData;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.solutionIssue.solution.domain.User;
import com.solutionIssue.solution.feature.user.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitData {

     private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;

     @PostConstruct
     public void initData() {
          if (userRepository.count() == 0) {
               User user = new User();
               user.setEmail("sotheakam201@gmail.com");
               user.setPassword(passwordEncoder.encode("12345"));
               user.setUserName("sothea");
               log.info("password : " + passwordEncoder.encode("12345"));
               userRepository.save(user);
          }
     }

}
