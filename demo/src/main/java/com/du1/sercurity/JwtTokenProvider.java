package com.du1.sercurity;

import java.net.http.HttpResponse;
import java.util.Date;

import com.du1.model.viewModel.userDetail;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtTokenProvider {
    private final String JWT_SECRET = "PH28978";

    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    private final long JWT_EXPIRATION = 604800000L;

    @Autowired
    private HttpServletResponse respon;

//    private Long l;

    public String generateToken(userDetail userDetails, String role) {
        System.out.println("ROle------------------- " +role);
        // Lấy thông tin user
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        // Tạo chuỗi json web token từ id của user.
//        l.toString();
        String jwt = Jwts.builder()
                .setSubject(Integer.toString(userDetails.getUsers().getId()))
//                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
        respon.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
        return jwt;

    }

    public Integer getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        System.out.println(Long.parseLong(claims.getSubject()) + " hummmmm");
        return Integer.parseInt(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
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

