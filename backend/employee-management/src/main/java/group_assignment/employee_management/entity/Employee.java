package group_assignment.employee_management.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String employeeNumber;

  @Column(nullable = false)
  @Size(max = 50, message = "50文字以内")
  @Pattern(
    regexp = "^(?!\\s)[ぁ-んァ-ヶ一-龯a-zA-Z\\s]+$"
  )
  private String name;

  @Column(nullable = false, unique = true)
  @Email
  @Size(max = 254)
  private String email;
  
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Department department;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Position position;

  @Column(nullable = false)
  private LocalDate hireDate;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Status status;

  protected Employee() {}

  public Employee(String employeeNumber, String name, String email, Department department, Position position, LocalDate hireDate, Status status) {
    this.employeeNumber = employeeNumber;
    this.name = name;
    this.email = email;
    this.department = department;
    this.position = position;
    this.hireDate = hireDate;
    this.status = status;
  }

  public Long getId() {
    return id;
  }
  public String getEmployeeNumber() {
    return employeeNumber;
  }
  public String getName() {
    return name;
  }
  public String getEmail() {
    return email;
  }
  public Department getDepartment() {
    return department;
  }
  public Position getPosition() {
    return position;
  }
  public LocalDate getHireDate() {
    return hireDate;
  }
  public Status getStatus() {
    return status;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public void setEmployeeNumber(String employeeNumber) {
    this.employeeNumber = employeeNumber;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setDepartment(Department department) {
    this.department = department;
  }
  public void setPosition(Position position) {
    this.position = position;
  }
  public void setHireDate(LocalDate hireDate) {
    this.hireDate = hireDate;
  }
  public void setStatus(Status status) {
    this.status = status;
  }
}
