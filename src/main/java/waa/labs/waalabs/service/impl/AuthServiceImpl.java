package waa.labs.waalabs.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import waa.labs.waalabs.domain.Role;
import waa.labs.waalabs.domain.User;
import waa.labs.waalabs.dto.request.LoginRequest;
import waa.labs.waalabs.dto.request.RefreshTokenRequest;
import waa.labs.waalabs.dto.request.SignUpRequest;
import waa.labs.waalabs.dto.response.LoginResponse;
import waa.labs.waalabs.dto.response.SignUpResponse;
import waa.labs.waalabs.repo.RoleRepo;
import waa.labs.waalabs.repo.UserRepo;
import waa.labs.waalabs.service.AuthService;
import waa.labs.waalabs.util.JwtUtil;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil, UserRepo userRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepo) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        log.info("Signing up user with email: {}", signUpRequest.getEmail());

        if (signUpRequest.getEmail() == null || signUpRequest.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (signUpRequest.getPassword() == null || signUpRequest.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (userRepo.findUserByEmail(signUpRequest.getEmail()) != null) {
            return SignUpResponse.builder()
                    .message("User already exists")
                    .build();
        }

        Role role = roleRepo.findRoleByName("USER");
        User user = User.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .firstname(signUpRequest.getFirstname())
                .lastname(signUpRequest.getLastname())
                .roles(List.of(role))
                .build();
        userRepo.save(user);

        return SignUpResponse.builder()
                .message("User registered successfully")
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .roles(user.getRoles())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            var result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            log.info("Bad credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String accessToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
        var loginRes = new LoginResponse(accessToken, refreshToken);
        return loginRes;
    }

    @Override
    public LoginResponse adminLogin(LoginRequest loginRequest) {
        log.info("Admin login attempt: {}", loginRequest.getEmail());

        User admin = userRepo.findUserByEmail("admin@example.com");

        if (!passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Invalid admin credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(admin.getEmail());
        // Generate JWT Token with ADMIN role
        String token = jwtUtil.generateToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(admin.getEmail());

        return new LoginResponse(token, refreshToken);
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            var isAccessTokenExpired = jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken());
            if(isAccessTokenExpired)
                System.out.println("Access token expired");
            else
                System.out.println("Access token not expired");

            final String accessToken = jwtUtil.doGenerateToken(jwtUtil.getSubjectFromToken(refreshTokenRequest.getRefreshToken()));
            var loginRes = new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
            return loginRes;
        }
        return new LoginResponse();
    }
}
