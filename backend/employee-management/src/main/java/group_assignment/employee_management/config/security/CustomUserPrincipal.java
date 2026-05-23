package group_assignment.employee_management.config.security;

public class CustomUserPrincipal {
  private Long id;

  public CustomUserPrincipal(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
