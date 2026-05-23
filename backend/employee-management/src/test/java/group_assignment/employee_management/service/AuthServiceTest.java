package group_assignment.employee_management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import group_assignment.employee_management.config.security.JwtProvider;
import group_assignment.employee_management.entity.Role;
import group_assignment.employee_management.entity.User;
import group_assignment.employee_management.repository.user.UserRepository;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
  @Mock
  private UserRepository userRepository;
  
  @Mock
  private JwtProvider jwtProvider;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks AuthService authService;

  @Test
  void authenticate_正しい認証情報ならJWTを返す() {
    String email = "test@example.com";
    String rawPassword = "password123";
    String encodedPassword = "hashed-password";
    Role role = Role.ADMIN;
    String token = "jwt-token";

    User user = new User(
      email,
      encodedPassword,
      role
    );
    user.setId(1L);

    when(userRepository.findByEmail(email))
      .thenReturn(Optional.of(user));

    when(passwordEncoder.matches(rawPassword, encodedPassword))
            .thenReturn(true);

    when(jwtProvider.generateToken(user.getId()))
                .thenReturn(token);

    String result = authService.authenticate(email, rawPassword);

    assertEquals(token, result);
  }
}
