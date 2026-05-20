package group_assignment.employee_management.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import group_assignment.employee_management.config.security.JwtProvider;
import group_assignment.employee_management.entity.User;
import group_assignment.employee_management.exception.AuthException;
import group_assignment.employee_management.repository.user.UserRepository;

@Service
public class AuthService {
  private final UserRepository userRepository;
  private final JwtProvider jwtProvider;
  private final PasswordEncoder passwordEncoder;

  public AuthService(UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.jwtProvider = jwtProvider;
    this.passwordEncoder = passwordEncoder;
  }

  public String authenticate(String email, String password) {
    User user = userRepository.findByEmail(email)
      .orElseThrow(() -> new AuthException("アカウントが確認出来ませんでした。正しいメールアドレスとパスワードをご入力ください。"));

    if(!passwordEncoder.matches(password, user.getPassword())) {
      throw new AuthException("アカウントが確認出来ませんでした。正しいメールアドレスとパスワードをご入力ください。");
    }
    return jwtProvider.generateToken(user.getId());
  }
}
