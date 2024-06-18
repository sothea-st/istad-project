package com.solutionIssue.solution.feature.auth;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

     @PostMapping("/add")
     public void add(@RequestParam("value") String value){

     }
     
}
