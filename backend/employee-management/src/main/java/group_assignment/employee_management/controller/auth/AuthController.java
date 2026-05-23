package group_assignment.employee_management.controller.auth;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group_assignment.employee_management.dto.auth.AuthRequestDto;
import group_assignment.employee_management.dto.auth.AuthResponseDto;
import group_assignment.employee_management.dto.common.ApiResponseDto;
import group_assignment.employee_management.service.auth.AuthService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ApiResponseDto<AuthResponseDto> login(@RequestBody AuthRequestDto req) {
    if(
      req.getEmail() == null || req.getEmail().isBlank() ||
      req.getPassword() == null || req.getPassword().isBlank()
    ) {
      throw new ValidationException("メールアドレス、パスワードの両方をご入力ください。");
    }

    String token = authService.authenticate(req.getEmail(), req.getPassword());

    AuthResponseDto res = new AuthResponseDto(token);

    return new ApiResponseDto<>("SUCCESS", res , null);
  } 
}
