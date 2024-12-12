package com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Service.Impl;

import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.LoginDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.SignUpDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.UserDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Entity.UserEntity;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Exception.ResourceNotFoundException;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Repository.UserRepository;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private  final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new BadCredentialsException("User not found with this email "+username));
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with this id "+userId));
    }

}
