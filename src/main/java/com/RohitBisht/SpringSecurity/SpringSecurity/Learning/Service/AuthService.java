package com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Service;

import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.LogResponseDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.LoginDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.SignUpDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.UserDTO;

public interface AuthService {

    UserDTO signUp(SignUpDTO signUpDTO);

    LogResponseDTO loginUser(LoginDTO login);

    LogResponseDTO refresherToken(String refreshToken);
}
