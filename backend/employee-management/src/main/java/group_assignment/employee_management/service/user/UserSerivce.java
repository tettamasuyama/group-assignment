package group_assignment.employee_management.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import group_assignment.employee_management.dto.common.ApiResponseDto;
import group_assignment.employee_management.dto.user.CreateUserRequestDto;
import group_assignment.employee_management.entity.User;
import group_assignment.employee_management.repository.user.UserRepository;
import jakarta.validation.ValidationException;

@Service
public class UserSerivce {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserSerivce(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void save(CreateUserRequestDto req) {
    if(userRepository.findByEmail(req.getEmail()) != null) {
      throw new ValidationException();
    }

    String hashedPassword = passwordEncoder.encode(req.getPassword());

    User user = new User(
      req.getEmail(),
      hashedPassword,
      req.getRole()
    );

    userRepository.save(user);
  }
}
