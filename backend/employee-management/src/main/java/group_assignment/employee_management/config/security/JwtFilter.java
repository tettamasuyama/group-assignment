package group_assignment.employee_management.config.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
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
        //トークンからユーザーIDを取得
        Long userId = jwtProvider.getUserId(token);

        //ユーザーIDを元にユーザー情報を取得
        User user = userRepository.findById(userId)
          .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

        //ユーザー情報を格納するPrincipalオブジェクトを作成
        CustomUserPrincipal principal = new CustomUserPrincipal(userId, user.getEmail(), user.getRole());

        //authenticationオブジェクトを作成
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal,null);

        //認証オブジェクトをセキュリティにセット
        //既に認証されている場合は上書きしない
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
          SecurityContextHolder.getContext().setAuthentication(auth); }
        
      } catch (ExpiredJwtException e) {
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
      } catch (JwtException e) {
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
      }

      filterChain.doFilter(req, res);
    }
  }
} 

  