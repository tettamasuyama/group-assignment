package group_assignment.employee_management.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import group_assignment.employee_management.controller.auth.AuthController;
import group_assignment.employee_management.dto.auth.AuthRequestDto;
import group_assignment.employee_management.dto.auth.AuthResponseDto;
import group_assignment.employee_management.dto.common.ApiResponseDto;
import group_assignment.employee_management.repository.user.UserRepository;
import group_assignment.employee_management.service.auth.AuthService;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
  @Mock AuthService authService;

  @Mock UserRepository userRepository;

  @InjectMocks AuthController authController;

  @Test void login_認証成功すればApiResponseDtoが返る() {

    String email = "test-" + System.currentTimeMillis() + "@example.com";
    String password = "password123";
    String token = "jwt-token";

    AuthRequestDto req = new AuthRequestDto(email, password);


    when(authService.authenticate(email, password))
    .thenReturn(token);

    ApiResponseDto<AuthResponseDto> result = authController.login(req);

    assertEquals(token, result.getData().getToken());
  }
}
