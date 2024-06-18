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

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
     private final UserRepository userRepository;

     // method loadUserByUsername for get userName and password from database
     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          User user = userRepository.findByUserName(username)
                    .orElseThrow(() -> new ResponseStatusException(
                              HttpStatus.NOT_FOUND,
                              "User has been not found "));

          CustomUserDetails customUserDetails = new CustomUserDetails();
          customUserDetails.setUser(user);
          return customUserDetails;
     }

}
