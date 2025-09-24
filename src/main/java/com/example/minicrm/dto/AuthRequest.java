package com.example.minicrm.dto;
import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
}
