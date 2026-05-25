package group_assignment.employee_management.controller.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group_assignment.employee_management.dto.common.ApiResponseDto;
import group_assignment.employee_management.service.user.UserSerivce;

@RestController
@RequestMapping("api/auth/register")
public class UserController {
  private UserSerivce userSerivce;

  public UserController(UserSerivce userSerivce) {
    this.userSerivce = userSerivce;
  }

  public ApiResponseDto<String> 
}
