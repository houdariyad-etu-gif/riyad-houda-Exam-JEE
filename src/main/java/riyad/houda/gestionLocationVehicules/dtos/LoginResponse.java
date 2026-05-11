package riyad.houda.gestionLocationVehicules.dtos;

import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String username;
    private String role;
}