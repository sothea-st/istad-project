package com.solutionIssue.solution.feature.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solutionIssue.solution.domain.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {

     Optional<User> findByUserName(String userName);

     Optional<User> findById(Integer id);

     Optional<User> findByEmail(String email);

     // select * from user where email = ? and user_name = ?
     Optional<User> findByEmailAndUserName(String email , String userName);
}
