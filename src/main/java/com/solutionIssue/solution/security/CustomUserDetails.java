package com.solutionIssue.solution.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.solutionIssue.solution.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class CustomUserDetails implements UserDetails{

     private User user;

     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
     }

     @Override
     public String getPassword() {
          return user.getPassword();
     }

     @Override
     public String getUsername() {
          return user.getUserName();
     }
     
}
