package co.istad.mbanking.features.auth.dto;

public record VerifyEmailRequest(
    String email,
    String digit
) {
    
}
