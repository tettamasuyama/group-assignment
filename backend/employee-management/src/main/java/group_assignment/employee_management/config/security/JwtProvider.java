package group_assignment.employee_management.config.security;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtProvider {
  @Value("${jwt.secret}")
  private String secret;

  private SecretKey secretKey;

  @Value("${jwt.expiration}")
  private String expiration;

  @PostConstruct
  public void setKey() {
    secretKey = Keys.hmacShaKeyFor(secret.getBytes());
  }
}
