package waa.labs.waalabs.service;

import waa.labs.waalabs.dto.request.LoginRequest;
import waa.labs.waalabs.dto.request.RefreshTokenRequest;
import waa.labs.waalabs.dto.request.SignUpRequest;
import waa.labs.waalabs.dto.response.LoginResponse;
import waa.labs.waalabs.dto.response.SignUpResponse;

public interface AuthService {
    SignUpResponse signUp(SignUpRequest signUpRequest);
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse adminLogin(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
