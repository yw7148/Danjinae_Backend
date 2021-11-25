package com.capstone.danjinae.user.JWT.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import com.capstone.danjinae.user.JWT.entity.RefreshToken;
import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.user.repository.TokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
    public final static long TOKEN_VALIDATION_SECOND = 1000L * 60 * 30; // 30분
    public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 60 * 24 * 30 * 6; // 6개월

    final static public String ACCESS_TOKEN_NAME = "ACCESS_TOKEN";
    final static public String REFRESH_TOKEN_NAME = "REFRESH_TOKEN";

    @Autowired
    private TokenRepository tokenRepository;

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY.getBytes("UTF-8")).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String getRefreshUsername(String token) {
        RefreshToken rToken = tokenRepository.findByRefreshToken(token);

        return rToken.getUsername();
    }

    public String getUsername(String token) {
        return extractAllClaims(token).get("username", String.class);
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public String generateToken(String username) {
        return doGenerateToken(username, TOKEN_VALIDATION_SECOND);
    }

    public String generateRefreshToken(User user) {
        String token = doGenerateToken(user.getPhone(), REFRESH_TOKEN_VALIDATION_SECOND);
        RefreshToken refreshToken = new RefreshToken(token, user.getPhone());

        tokenRepository.save(refreshToken);

        return token;
    }

    public boolean deleteToken(String token) throws Exception {
        try {
            RefreshToken refreshtoken = tokenRepository.findByRefreshToken(token);
            tokenRepository.delete(refreshtoken);

            return true;
        } catch (Exception e) {
            throw new Exception("DB Error : " + e.getMessage());
        }
    }

    public String doGenerateToken(String username, long expireTime) {

        Claims claims = Jwts.claims();
        claims.put("username", username);

        String jwt = Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()).compact();

        return jwt;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
