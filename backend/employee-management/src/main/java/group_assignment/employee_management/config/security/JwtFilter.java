package group_assignment.employee_management.config.security;

import java.io.IOException;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
  protected void doFilterInternal(
    HttpServletRequest req, 
    HttpServletResponse res, 
    FilterChain filterChain
  ) throws ServletException, IOException {
    String authHeader = req.getHeader("Authorization");

    if(authHeader == null) {
      filterChain.doFilter(req, res);
      return;
    }

    if(!authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(req, res);
      return;
    }

    String token = authHeader.substring(7);

    try {
      if(!jwtProvider.validateToken(token)) {
        filterChain.doFilter(req, res);
        return;
      }
      Long userId = jwtProvider.getUserId(token);
      CustomUserPrincipal principal = new CustomUserPrincipal(userId);

      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal,null,null);

      SecurityContextHolder
        .getContext()
        .setAuthentication(auth);
    } catch (AuthException e) {
      SecurityContextHolder.clearContext();
    }

    filterChain.doFilter(req, res);
  }
}


  