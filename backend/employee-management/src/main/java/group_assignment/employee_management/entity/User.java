package group_assignment.employee_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "メールアドレス必須")
  @Column(nullable =false, unique = true)
  private String email;

  @NotBlank(message = "パスワード必須")
  @Column(nullable = false)
  @Size(min = 8, max = 64)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  protected User() {}

  public User(String email, String password, Role role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public Long getId() {
    return id;
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

  public void setId(Long id) {
    this.id = id;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public void setRole(Role role) {
    this.role = role;
  }
}
