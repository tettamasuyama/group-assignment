package group_assignment.employee_management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import group_assignment.employee_management.dto.user.CreateUserRequestDto;
import group_assignment.employee_management.entity.Role;
import group_assignment.employee_management.entity.User;
import group_assignment.employee_management.repository.user.UserRepository;
import group_assignment.employee_management.service.user.UserSerivce;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @Mock
  UserRepository userRepository;

  @Mock PasswordEncoder passwordEncoder;

  @InjectMocks UserSerivce userService;

  @Test void save_DBにUserを新規登録を保存() {
    String email = "test@example.com";
    String password = "password123";
    Role role = Role.ADMIN;

    CreateUserRequestDto req = new CreateUserRequestDto(email, password, role);

    when(passwordEncoder.encode(password))
    .thenReturn("encodedPassword");

    when(userRepository.findByEmail(email))
    .thenReturn(null);

    // service実行
    userService.save(req);

    // 検証
    ArgumentCaptor<User> capter = ArgumentCaptor.forClass(User.class);

    verify(userRepository).save(capter.capture());

    User result = capter.getValue();

    assertEquals(email, result.getEmail());
    assertEquals("encodedPassword", result.getPassword());
    assertEquals(role, result.getRole());
  }
}
