package group_assignment.employee_management.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import group_assignment.employee_management.controller.user.UserController;
import group_assignment.employee_management.dto.common.ApiResponseDto;
import group_assignment.employee_management.dto.user.CreateUserRequestDto;
import group_assignment.employee_management.entity.Role;
import group_assignment.employee_management.repository.user.UserRepository;
import group_assignment.employee_management.service.user.UserSerivce;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

  @Mock UserSerivce userService;
  @InjectMocks UserController usercontroller;

  @Test void createUser_登録成功すればApiResponseDtoが返る() {

    String email = "test-" + System.currentTimeMillis() + "@example.com";
    String password = "password123";
    Role role = Role.ADMIN;

    ApiResponseDto<Void> mockResponse =
        new ApiResponseDto<>(
          "SUCCESS",
          null,
          "アカウントの作成が完了しました。"
        );

    CreateUserRequestDto req = new CreateUserRequestDto(email, password, role);

    ApiResponseDto<Void> result = usercontroller.createUser(req);

    assertEquals(mockResponse.getStatus(), result.getStatus());
    assertEquals(mockResponse.getData(), result.getData());
    assertEquals(mockResponse.getMessage(), result.getMessage());
  }
}
