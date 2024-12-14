package com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogResponseDTO {

    private Long id;
    private String accessToken;
    private String refreshToken;
}
