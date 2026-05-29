package group_assignment.employee_management.dto.emoloyee;

import java.time.LocalDate;

import group_assignment.employee_management.entity.Department;
import group_assignment.employee_management.entity.Position;
import group_assignment.employee_management.entity.Status;

public class EmployeeResponseForAdminDto {
  private String employeeNumber;
  private String name;
  private String email;
  private Department department;
  private Position position;
  private LocalDate hireDate;
  private Status status;

  public EmployeeResponseForAdminDto(
    String employeeNumber,
    String name,
    String email,
    Department department,
    Position position,
    LocalDate hireDate,
    Status status
  ) {
    this.employeeNumber = employeeNumber;
    this.name = name;
    this.email = email;
    this.department = department;
    this.position = position;
    this.hireDate = hireDate;
    this.status = status;
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
