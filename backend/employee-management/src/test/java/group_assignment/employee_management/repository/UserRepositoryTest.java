package group_assignment.employee_management.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import group_assignment.employee_management.entity.Role;
import group_assignment.employee_management.entity.User;
import group_assignment.employee_management.repository.user.UserRepository;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class UserRepositoryTest {

  @Autowired UserRepository userRepository;

  @Test void findByEmail_存在するemailならUserを返す() {
    String email = "test-" + System.currentTimeMillis() + "@example.com";
    String password = "password123";
    Role role = Role.ADMIN;

    User user = new User(
      email,
      password,
      role
    );
    userRepository.save(user);

    Optional<User> result = userRepository.findByEmail(email);

    assertTrue(result.isPresent());
    assertEquals(email, result.get().getEmail());
    assertEquals(password, result.get().getPassword());
    assertEquals(role, result.get().getRole());
  }
}
