package com.example.apispringjwt.service.impl;

import com.example.apispringjwt.service.IJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtService implements IJwtService {

        @Autowired
        private  JwtEncoder jwtEncoder;

        @Override
        public String generateToken(Authentication authentication) {
            Instant now = Instant.now();
            // Create a token that contains email of the user as subject
            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(now)
                    .expiresAt(now.plus(1, ChronoUnit.DAYS))
                    .subject(authentication.getName())
                    .build();
            // Encoding and returning the token
            JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
            return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        }
}
