package riyad.houda.gestionLocationVehicules.web;

import riyad.houda.gestionLocationVehicules.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);

        LoginResponse response = new LoginResponse();
        response.setAccessToken(token);
        response.setUsername(userDetails.getUsername());
        response.setRole(userDetails.getAuthorities().iterator().next().getAuthority());

        return response;
    }
}

class LoginRequest {
    private String username;
    private String password;

}

class LoginResponse {
    private String accessToken;
    private String username;
    private String role;

}
