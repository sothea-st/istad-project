package com.solutionIssue.solution.feature.auth;

import com.solutionIssue.solution.feature.auth.dto.AuthResponse;
import com.solutionIssue.solution.feature.auth.dto.LoginRequest;

public interface AuthService {
     AuthResponse login(LoginRequest loginRequest);
}
