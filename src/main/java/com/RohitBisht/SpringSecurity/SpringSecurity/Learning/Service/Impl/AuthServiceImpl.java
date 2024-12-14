package com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Service.Impl;

import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.LogResponseDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.LoginDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.SignUpDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.UserDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Entity.UserEntity;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Repository.UserRepository;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Service.AuthService;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public UserDTO signUp(SignUpDTO signUpDTO) {

        Optional<UserEntity> user = userRepository.findByEmail(signUpDTO.getEmail());
        if(user.isPresent()) {
            throw  new BadCredentialsException("User already present with email "+signUpDTO.getEmail());
        }

        UserEntity toBeCreated = modelMapper.map(signUpDTO, UserEntity.class);

        //Encode the password
        toBeCreated.setPassword(passwordEncoder.encode(toBeCreated.getPassword()));

        UserEntity savedUser = userRepository.save(toBeCreated);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    public LogResponseDTO loginUser(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        UserEntity user = (UserEntity) authentication.getPrincipal();

        String accessToken = jwtService.generateAcessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new LogResponseDTO(user.getId(), refreshToken, accessToken);
    }

    public LogResponseDTO refresherToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);

        UserEntity user = userService.getUserById(userId);

        String acessToken = jwtService.generateAcessToken(user);

        return new LogResponseDTO(user.getId(), acessToken, refreshToken);
    }
}
