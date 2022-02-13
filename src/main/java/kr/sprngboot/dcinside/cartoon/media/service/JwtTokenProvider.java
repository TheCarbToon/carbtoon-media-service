package kr.sprngboot.dcinside.cartoon.media.service;

import io.jsonwebtoken.Claims;

public interface JwtTokenProvider {

    Claims getClaimsFromJWT(String token);

    boolean validateToken(String authToken);

}
