package co.istad.mbanking.features.auth;

import co.istad.mbanking.features.auth.dto.RegisterRequest;
import co.istad.mbanking.features.auth.dto.RegisterResponse;
import co.istad.mbanking.features.auth.dto.SendMailRequest;
import co.istad.mbanking.features.auth.dto.VerifyEmailRequest;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/email")
    public void sendMail(@RequestBody SendMailRequest sendMailRequest) throws MessagingException {
        authService.sendMail(sendMailRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/verify-code")
    public void veriyCode(@RequestBody VerifyEmailRequest verifyEmailRequest) {
        authService.verifyEmail(verifyEmailRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/resend-code")
    public void resedCode(@RequestBody SendMailRequest sendMailRequest) throws MessagingException {
        authService.sendMail(sendMailRequest);
    }

}
