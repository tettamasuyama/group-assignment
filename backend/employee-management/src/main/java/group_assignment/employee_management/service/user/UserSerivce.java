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

  // public void save(CreateUserRequestDto req) {
  //   if(userRepository.findByEmail(req.getEmail()) != null) {
  //     throw new ValidationException();
  //   }

  public void save(CreateUserRequestDto request) {
    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
      throw new ValidationException("このメールアドレスは既に登録されています。");
    }

    String hashedPassword = passwordEncoder.encode(request.getPassword());

    User user = new User(
      request.getEmail(),
      hashedPassword,
      reqquest.getRole()
    );

    userRepository.save(user);
  }
}
