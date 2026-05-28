package group_assignment.employee_management.controller.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group_assignment.employee_management.dto.common.ApiResponseDto;
import group_assignment.employee_management.dto.user.CreateUserRequestDto;
import group_assignment.employee_management.service.user.UserSerivce;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth/register")
public class UserController {
  private UserSerivce userSerivce;

  public UserController(UserSerivce userSerivce) {
    this.userSerivce = userSerivce;
  }
  @PostMapping
  public ApiResponseDto<Void> createUser(@Valid @RequestBody CreateUserRequestDto req) {
    userSerivce.save(req);
    return new ApiResponseDto<>("SUCCESS", null, "アカウントの作成が完了しました。");
  }
}
