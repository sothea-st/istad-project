package co.istad.mbanking.features.auth;

import co.istad.mbanking.domain.Role;
import co.istad.mbanking.domain.User;
import co.istad.mbanking.features.auth.dto.RegisterRequest;
import co.istad.mbanking.features.auth.dto.RegisterResponse;
import co.istad.mbanking.features.user.RoleRepository;
import co.istad.mbanking.features.user.UserMapper;
import co.istad.mbanking.features.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {

        // validation phone number
        if( userRepository.existsByPhoneNumber(registerRequest.phoneNumber()) ) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number already in use"
            );
        }

        // validation email
        if( userRepository.existsByEmail(registerRequest.email()) ) {
            throw  new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already in use"
            );
        }

        // validation password and confirmedPassword
        if( !registerRequest.password().equals(registerRequest.confirmedPassword()) ) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "passwords do not match"
            );
        }

        // validation national card id
        if( userRepository.existsByNationalCardId(registerRequest.nationalCardId()) ) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "National card already in use"
            );
        }

        // validation accept term
        if( !registerRequest.acceptTerm() ) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "You must have accept term!."
            );
        }

        User user = userMapper.toUser(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setUuid(UUID.randomUUID().toString());
        user.setIsDeleted(false);
        user.setIsBlocked(false);
        user.setProfileImage("profile/default-user.png");

        // get role
        Role userRole = roleRepository.findRoleUser();
        Role customerRole = roleRepository.findRoleCustomer();

        List<Role> roles = List.of(userRole,customerRole);
        user.setRoles(roles);

        userRepository.save(user);

        return RegisterResponse.builder()
                .message("You have successfully registered , please verify")
                .email(registerRequest.email())
                .build();
    }
}
