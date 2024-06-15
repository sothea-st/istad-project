package co.istad.mbanking.features.auth;

import co.istad.mbanking.domain.Role;
import co.istad.mbanking.domain.User;
import co.istad.mbanking.domain.Verify;
import co.istad.mbanking.features.auth.dto.RegisterRequest;
import co.istad.mbanking.features.auth.dto.RegisterResponse;
import co.istad.mbanking.features.auth.dto.SendMailRequest;
import co.istad.mbanking.features.auth.dto.VerifyEmailRequest;
import co.istad.mbanking.features.user.RoleRepository;
import co.istad.mbanking.features.user.UserMapper;
import co.istad.mbanking.features.user.UserRepository;
import co.istad.mbanking.features.user.VerifyRepository;
import co.istad.mbanking.utils.UtilRandom;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

        private final UserRepository userRepository;
        private final UserMapper userMapper;
        private final PasswordEncoder passwordEncoder;
        private final RoleRepository roleRepository;
        private final JavaMailSender javaMailSender;
        private final VerifyRepository verifyRepository;

        @Value("${spring.mail.username}")
        private String adminEmail;

        @Override
        public void verifyEmail(VerifyEmailRequest verifyEmailRequest) {
                // validation check email exist in db or not
                User user = userRepository.findByEmail(verifyEmailRequest.email())
                                .orElseThrow(() -> new ResponseStatusException(
                                                HttpStatus.NOT_FOUND,
                                                "Email has not been found ."));

                // validation check verify code
                Verify verify = verifyRepository.findByUserAndDigit(user, verifyEmailRequest.digit())
                                .orElseThrow(() -> new ResponseStatusException(
                                                HttpStatus.NOT_FOUND,
                                                "Verify code had not been found ."));

                // validation check code expired or not
                if (LocalTime.now().isAfter(verify.getExpiredTime())) {
                        throw new ResponseStatusException(
                                        HttpStatus.FORBIDDEN,
                                        "Code already expired .");
                }

                // change isVerify field to true
                User user2 = userRepository.findById(user.getId())
                                .orElseThrow(() -> new ResponseStatusException(
                                                HttpStatus.NOT_FOUND,
                                                "Id has not been found ."));

                user2.setIsVerifies(true);
                userRepository.save(user2);

                // delete verify
                verifyRepository.delete(verify);

        }

        @Override
        public void sendMail(SendMailRequest sendMailRequest) throws MessagingException {
                // random 6 digit
                String digit = UtilRandom.randomDigit();

                // validation check email exist in db or not
                User user = userRepository.findByEmail(sendMailRequest.email())
                                .orElseThrow(() -> new ResponseStatusException(
                                                HttpStatus.NOT_FOUND,
                                                "Email has not been found ."));

                /* 
                *       check if email exist  and code already expired  
                *        if true  delete it
                */ 

                Optional<Verify> verifyOp = verifyRepository.findByUser(user);
                if( verifyOp.isPresent() ) {
                        verifyRepository.delete(verifyOp.get());
                }
                               

                // save user and digit to verify
                Verify verify = new Verify();
                verify.setDigit(digit);
                verify.setExpiredTime(LocalTime.now().plusMinutes(1));
                verify.setUser(user);
                verifyRepository.save(verify);

                // send digit to email
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);
                helper.setFrom(adminEmail);
                helper.setTo(sendMailRequest.email());
                helper.setSubject("Digit for verify");
                helper.setText(digit);
                javaMailSender.send(message);
        }

        @Override
        public RegisterResponse register(RegisterRequest registerRequest) {

                // validation phone number
                if (userRepository.existsByPhoneNumber(registerRequest.phoneNumber())) {
                        throw new ResponseStatusException(
                                        HttpStatus.CONFLICT,
                                        "Phone number already in use");
                }

                // validation email
                if (userRepository.existsByEmail(registerRequest.email())) {
                        throw new ResponseStatusException(
                                        HttpStatus.CONFLICT,
                                        "Email already in use");
                }

                // validation password and confirmedPassword
                if (!registerRequest.password().equals(registerRequest.confirmedPassword())) {

                        throw new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST,
                                        "passwords do not match");
                }

                // validation national card id
                if (userRepository.existsByNationalCardId(registerRequest.nationalCardId())) {
                        throw new ResponseStatusException(
                                        HttpStatus.CONFLICT,
                                        "National card already in use");
                }

                // validation accept term
                if (!registerRequest.acceptTerm()) {
                        throw new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST,
                                        "You must have accept term!.");
                }

                User user = userMapper.toUser(registerRequest);
                user.setPassword(passwordEncoder.encode(registerRequest.password()));
                user.setUuid(UUID.randomUUID().toString());
                user.setIsDeleted(false);
                user.setIsBlocked(false);
                user.setProfileImage("profile/default-user.png");
                user.setIsVerifies(false);

                // get role
                Role userRole = roleRepository.findRoleUser();
                Role customerRole = roleRepository.findRoleCustomer();

                List<Role> roles = List.of(userRole, customerRole);
                user.setRoles(roles);

                userRepository.save(user);

                return RegisterResponse.builder()
                                .message("You have successfully registered , please verify")
                                .email(registerRequest.email())
                                .build();
        }

}
