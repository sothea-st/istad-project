package co.istad.mbanking.features.auth;

import co.istad.mbanking.features.auth.dto.RegisterRequest;
import co.istad.mbanking.features.auth.dto.RegisterResponse;
import co.istad.mbanking.features.auth.dto.SendMailRequest;
import co.istad.mbanking.features.auth.dto.VerifyEmailRequest;
import jakarta.mail.MessagingException;
 
 

public interface AuthService {
    /**
     * Register
     * @return RegisterResponse
     */
    RegisterResponse register(RegisterRequest registerRequest);

    /*
     * send mail
     */
    void sendMail(SendMailRequest sendMailRequest) throws MessagingException;

    /*
     * verify  email with digit
     * for : user can login
     */
    void verifyEmail(VerifyEmailRequest verifyEmailRequest);
}
