package group_assignment.employee_management.config.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtProvider {
  @Value("${jwt.secret}")
  private String secret;

  private SecretKey secretKey;

  @Value("${jwt.expiration}")
  private Long expiration;

  @PostConstruct
  public void setKey() {
    secretKey = Keys.hmacShaKeyFor(secret.getBytes());
  }

  private Claims getClaims(String token) {
      return Jwts.parserBuilder()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public String generateToken(Long userId, String role) {
    return Jwts.builder()
      .setSubject(userId.toString())
      .claim("role", role)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + expiration))
      .signWith(secretKey)
      .compact();
  }

  public boolean validateToken(String token) {
    try {
      getClaims(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public Long getUserId(String token) {
    try {
      return Long.parseLong(getClaims(token).getSubject());
    } catch (NumberFormatException e) {
      throw new RuntimeException("Invalid userId format in token");
    } catch (Exception e) {
      return null;
    }
  }

  public String getRole(String token) {
    try {
      return getClaims(token).get("role", String.class);
    } catch (Exception e) {
      return null;
    }
  }
}