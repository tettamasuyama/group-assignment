package group_assignment.employee_management.dto.auth;

public class AuthRequestDto {
  private String email;
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
