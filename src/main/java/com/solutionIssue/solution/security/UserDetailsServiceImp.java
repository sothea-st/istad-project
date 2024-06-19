package com.solutionIssue.solution.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.solutionIssue.solution.domain.User;
import com.solutionIssue.solution.feature.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImp implements UserDetailsService {
     private final UserRepository userRepository;

     // method loadUserByUsername for get userName and password from database
     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new ResponseStatusException(
                              HttpStatus.NOT_FOUND,
                              "Email has been not found "));

                              log.info("auth user {}",user);


          CustomUserDetails customUserDetails = new CustomUserDetails();
          customUserDetails.setUser(user);
          return customUserDetails;
     }

}
