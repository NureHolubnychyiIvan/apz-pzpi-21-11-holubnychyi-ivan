package com.system.fridges.service;

import com.system.fridges.models.transferObjects.authenticationObjects.JwtAuthenticationResponse;
import com.system.fridges.models.transferObjects.authenticationObjects.RefreshTokenRequest;
import com.system.fridges.models.transferObjects.userObjects.SignInRequest;
import com.system.fridges.service.interfaces.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    /** 24 hours in milliseconds */
    private static final long TOKEN_EXPIRATION_TIME = 1000 * 60 * 24;

    /** 7 days in milliseconds */
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 604800000;

    private final static String SECRET_KEY = "413F442847284862506553685660597033733676397924422645294848406351";

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(),
                signInRequest.getPassword())
        );

        var user = customUserDetailsService.loadUserByUsername(signInRequest.getEmail());
        var webToken = generationToken(user);
        var refreshToken = generationRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(webToken);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String email = extractUserName(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
    }

    @Override
    public String generationToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generationRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigninKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshToken) {
        String email = extractUserName(refreshToken.getToken());
        UserDetails user = customUserDetailsService.loadUserByUsername(email);

        if (isTokenValid(refreshToken.getToken(), user)) {
            var webToken = generationToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(webToken);
            jwtAuthenticationResponse.setRefreshToken(refreshToken.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
