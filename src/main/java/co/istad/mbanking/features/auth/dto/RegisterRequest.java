package co.istad.mbanking.features.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Phone number is required")
        @Size(min = 9 , max = 10 , message = "Phone number must be between 9 to 10 digits")
        @Pattern(regexp = "^\\d+$" , message = "Phone number must be digit.")
        String phoneNumber,

        @NotBlank(message = "Email is required")
        @Pattern(regexp = "^\\S+@\\S+\\.\\S+$", message = "Email must be contain @ ")
        String email,

        @NotBlank(message = "Pin is required")
        @Size(min = 4 , max = 4)
        String pin,

        @NotBlank(message = "Password is required")
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message = "" +
                        "Password must be 8 character , 1 upper case , 1 digit , 1 special ")
        String password,

        @NotBlank(message = "Confirmed Password is required")
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message = "" +
                "Password must be 8 character , 1 upper case , 1 digit , 1 special ")
        String confirmedPassword,

        @NotBlank(message = "National Card ID is required")
        String nationalCardId,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Gender is required")
        String gender,

        @NotNull(message = "Term must be accept")
        Boolean acceptTerm
) {
}
