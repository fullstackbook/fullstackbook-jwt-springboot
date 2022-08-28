package com.example.fullstackbookjwtspringboot;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Date;

@SpringBootTest
@Log4j2
class FullstackbookJwtSpringbootApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = new TestingAuthenticationToken("username", "password", "ROLE_USER");
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        SecurityContext context2 = SecurityContextHolder.getContext();
        Authentication authentication2 = context2.getAuthentication();
        String username = authentication2.getName();
        Object principal = authentication2.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication2.getAuthorities();
        log.info("username = " + username);
        log.info("principal = " + principal);
        log.info("authorities = " + authorities);

        String jwtSecret = "foobar";
        String token = Jwts
                .builder()
                .setSubject("username")
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 1000))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();

        log.info("token = " + token);

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            log.info("jwt token is valid");
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
