package group_assignment.employee_management.dto;

import java.time.LocalDate;

import group_assignment.employee_management.entity.Department;
import group_assignment.employee_management.entity.Position;
import group_assignment.employee_management.entity.Status;

public class EmployeeRequestForAdminDto {
  private String name;
  private String email;
  private Department department;
  private Position position;
  private LocalDate hireDate;
  private Status status;

  public EmployeeRequestForAdminDto(String name, String email, Department department, Position position, LocalDate hireDate, Status status) {
    this.name = name;
    this.email = email;
    this.department = department;
    this.position = position;
    this.hireDate = hireDate;
    this.status = status;
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
}
