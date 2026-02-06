package com.example.securityapp.DTO;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Data
public class LoginResponseDTO {

    private String access_token;
    private String refresh_token;

    public LoginResponseDTO(String access_token, String refresh_token) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }
}
