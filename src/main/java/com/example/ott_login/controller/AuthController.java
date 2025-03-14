package com.example.ott_login.controller;

import com.example.ott_login.model.TokenResponse;
import com.example.ott_login.serivce.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    @GetMapping("/generate-token")
    public String generateToken(@RequestParam String username) {
        // in a real application the token should be sent via email
        // and not be returned from the rest endpoint
        return authService.generateOtt(username);
    }

    @GetMapping("/validate-token")
    public TokenResponse validateToken(@RequestParam String token) {
        return authService.validateOtt(token);

//        var authentication = authenticationManager.authenticate(new OneTimeTokenAuthenticationToken(token));
//        SecurityContextHolder.getContext().setAuthentication(authentication);

//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(authentication);
//        securityContextRepository.saveContext(context, request, response);

//        var token = jwtUtilities.generateToken(authentication.getPrincipal().toString());
//        return new TokenResponse("new token");
    }
}
