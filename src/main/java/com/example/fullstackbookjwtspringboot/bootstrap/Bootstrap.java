package com.example.fullstackbookjwtspringboot.bootstrap;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.util.Collection;
import java.util.Date;

@Component
@Log4j2
public class Bootstrap implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("bootstrap...");

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = new TestingAuthenticationToken("username", "password", "ROLE_USER");
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        SecurityContext context2 = SecurityContextHolder.getContext();
        Authentication authentication2 = context2.getAuthentication();
        String username = authentication2.getName();
        Object principal = authentication2.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication2.getAuthorities();
        System.out.println("username = " + username);
        System.out.println("principal = " + principal);
        System.out.println("authorities = " + authorities);

        String token = Jwts
                .builder()
                .setSubject("username")
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 1000))
                .signWith(SignatureAlgorithm.HS256, "jwt_secret")
                .compact();

        System.out.println("token = " + token);

        try {
            Jwts.parser().setSigningKey("jwt_secret").parseClaimsJws(token);
            log.info("jwt token valid");
        }  catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

    }
}
