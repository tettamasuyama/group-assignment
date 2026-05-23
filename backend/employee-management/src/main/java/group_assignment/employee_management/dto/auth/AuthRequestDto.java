package group_assignment.employee_management.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthRequestDto {

  @NotBlank(message = "メールアドレス必須")
  @Email(message = "メール形式が不正")
  @Size(max = 254)
  private String email;


  @NotBlank(message = "パスワード必須")
  @Size(min = 8, max = 64)
  private String password;

  public AuthRequestDto(String email, String password) {
    this.email = email;
    this.password = password;
  }


  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
