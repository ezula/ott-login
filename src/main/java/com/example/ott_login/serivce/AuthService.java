package com.example.ott_login.serivce;

import com.example.ott_login.model.TokenResponse;
import com.example.ott_login.security.JwtUtilities;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ott.GenerateOneTimeTokenRequest;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.authentication.ott.OneTimeTokenAuthenticationToken;
import org.springframework.security.authentication.ott.OneTimeTokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final OneTimeTokenService oneTimeTokenService;
    private final JwtUtilities jwtUtilities;
//    private final SecurityContextRepository securityContextRepository =
//            new HttpSessionSecurityContextRepository();

    public AuthService(final AuthenticationManager authenticationManager,
                       final OneTimeTokenService oneTimeTokenService,
                       final JwtUtilities jwtUtilities) {
        this.authenticationManager = authenticationManager;
        this.oneTimeTokenService = oneTimeTokenService;
        this.jwtUtilities = jwtUtilities;
    }

    public TokenResponse validateOtt(String oneTimeToken) {
        var authentication = authenticationManager.authenticate(new OneTimeTokenAuthenticationToken(oneTimeToken));
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(authentication);
//        securityContextRepository.saveContext(context, null, null);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        var token = jwtUtilities.generateToken(userDetails.getUsername());
        return new TokenResponse(token);
    }

    public String generateOtt(String username) {
        OneTimeToken token = oneTimeTokenService.generate(new GenerateOneTimeTokenRequest(username));
        // check is user exists, if not create user and store in db
        // one time token should be sent to user email, not returned as response
        return "Generated Token: " + token.getTokenValue();
    }
}
