package group_assignment.controller;
d
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group_assignment.employee_management.dto.ApiResponseDto;
import group_assignment.employee_management.dto.AuthRequestDto;
import group_assignment.employee_management.dto.AuthResponseDto;
import group_assignment.employee_management.service.AuthService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
