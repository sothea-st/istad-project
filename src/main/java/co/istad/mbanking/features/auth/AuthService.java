package co.istad.mbanking.features.auth;

import co.istad.mbanking.features.auth.dto.RegisterRequest;
import co.istad.mbanking.features.auth.dto.RegisterResponse;

public interface AuthService {
    /**
     * Register
     * @return RegisterResponse
     */
    RegisterResponse register(RegisterRequest registerRequest);
}
