package com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Controller;


import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.LogResponseDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.LoginDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.SignUpDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.UserDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Exception.ResourceNotFoundException;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Service.AuthService;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Service.PostServices;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Value("${deploy.env}")
    private String deployEnv;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        UserDTO userDTO = authService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LogResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        LogResponseDTO logResponseDTO = authService.loginUser(loginDTO);

        //Saving token in cookie
        Cookie cookie = new Cookie("refreshToken", logResponseDTO.getRefreshToken());
        cookie.setHttpOnly(true);

        cookie.setSecure("production".equals(deployEnv));//When development mode then HTTPS used for more secure
        response.addCookie(cookie);

        return ResponseEntity.ok(logResponseDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LogResponseDTO> refresh(HttpServletRequest request) {

        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresher token not found inside cookie"));

        LogResponseDTO logResponseDTO = authService.refresherToken(refreshToken);
        return ResponseEntity.ok(logResponseDTO);
    }
}
