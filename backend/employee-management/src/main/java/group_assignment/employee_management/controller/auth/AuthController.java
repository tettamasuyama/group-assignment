package group_assignment.employee_management.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import group_assignment.employee_management.dto.auth.AuthRequestDto;
import group_assignment.employee_management.dto.common.ApiResponseDto;
import group_assignment.employee_management.service.auth.AuthService;

@RestController
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ApiResponseDto<String> login(AuthRequestDto req) {
    String token = authService.authenticate(req.getEmail(), req.getPassword());
    return new ApiResponseDto<>("SUCCESS", token);
  }
}

じかい：テスト