package group_assignment.employee_management.dto;

public class AuthRequestDto {
  private String email;
  private String password;

  private AuthRequestDto(String email, String password) {
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
