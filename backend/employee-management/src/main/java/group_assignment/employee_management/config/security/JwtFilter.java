package group_assignment.employee_management.config.security;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {
  private final JwtProvider jwtProvider;

  public JwtFilter(JwtProvider jwtProvider) {
    this.jwtProvider = jwtProvider;
  } 
  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
    String authHeader = req.getHeader("Authorization");
    if(authHeader != null && authHeader.startsWith("Bearer ")) {
      String token = authHeader.substring(7);
      try {
        Long userId = jwtProvider.getUserId(token);
        req.setAttribute("userId", userId);
      } catch (JwtException e) {
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
      }
    }
    filterChain.doFilter(req, res);
  }
} 

