package com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Service;

import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.LoginDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.SignUpDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.UserDTO;

public interface AuthService {

    UserDTO signUp(SignUpDTO signUpDTO);

    String loginUser(LoginDTO login);
}
