package kr.sprngboot.dcinside.cartoon.media.service.impl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import kr.sprngboot.dcinside.cartoon.media.config.JwtConfig;
import kr.sprngboot.dcinside.cartoon.media.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class JwtTokenProviderImpl implements JwtTokenProvider {

    private final JwtConfig jwtConfig;

    @Override
    public Claims getClaimsFromJWT(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtConfig.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtConfig.getKey())
                    .build()
                    .parseClaimsJws(authToken);
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
