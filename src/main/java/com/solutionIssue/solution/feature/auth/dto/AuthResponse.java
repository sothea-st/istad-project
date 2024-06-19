package com.solutionIssue.solution.feature.auth.dto;

import lombok.Builder;

@Builder
public record AuthResponse(
     String accessToken,
     String refreshToken,
     String tokenType
) {
     
}
