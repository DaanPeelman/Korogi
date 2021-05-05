package com.korogi.rest.security;

import java.util.Collections;
import java.util.Date;
import com.korogi.rest.config.KorogiProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;

/**
 * @author Daan Peelman
 */
@Component

@Slf4j
@RequiredArgsConstructor
public class TokenProvider {
    private final KorogiProperties properties;

    public String createToken(Authentication authentication) {
        DefaultOidcUser userPrincipal = (DefaultOidcUser) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + properties.getOAuth().getTokenExpirationMSec());

        return Jwts.builder()
                   .setSubject(userPrincipal.getName())
                   .addClaims(Collections.singletonMap("email", userPrincipal.getEmail()))
                   .setIssuedAt(new Date())
                   .setExpiration(expiryDate)
                   .signWith(SignatureAlgorithm.HS512, properties.getOAuth().getTokenSecret())
                   .compact();
    }

    public Claims getUserIdFromToken(String token) {
        return Jwts.parser()
                   .setSigningKey(properties.getOAuth().getTokenSecret())
                   .parseClaimsJws(token)
                   .getBody();
    }

    public boolean isValidToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(properties.getOAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
