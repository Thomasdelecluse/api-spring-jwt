package com.example.apispringjwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService{
        @Value("${jwt.secret}")
        private String secret; // Clé secrète pour signer le JWT, définie dans les propriétés


        @Value("${jwt.expiration}")
        private int expiration; // Durée de validité du JWT, en millisecondes, définie dans les propriétés

        public String generateToken(String username) {
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + expiration);

            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
        }

        // Méthode pour extraire le nom d'utilisateur du JWT
        public String extractUsername(String token) {
            return Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token).getBody().getSubject();
        }

        // Valide le JWT en comparant le nom d'utilisateur et vérifiant l'expiration
        public Boolean validateToken(String token, UserDetails userDetails) {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }

        // Méthode pour vérifier si le JWT est expiré
        private Boolean isTokenExpired(String token) {
            return Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token).getBody().getExpiration().before(new Date());
        }

        // Méthode pour obtenir la clé secrète
        private SecretKey getKey() {
            return Keys.hmacShaKeyFor(secret.getBytes());
        }
}
