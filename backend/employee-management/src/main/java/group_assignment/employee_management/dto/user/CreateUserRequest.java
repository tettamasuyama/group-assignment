package group_assignment.employee_management.dto.user;

import group_assignment.employee_management.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {

  @NotBlank(message = "メールアドレス必須")
  @Email(message = "メール形式が不正")
  @Size(max = 254)
  private String email;

  @NotBlank(message = "パスワード必須")
  @Size(min = 8, max = 64)
  private String password;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Role role;

  public CreateUserRequest(String email, String password, Role role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Role getRole() {
    return role;
  }
}
