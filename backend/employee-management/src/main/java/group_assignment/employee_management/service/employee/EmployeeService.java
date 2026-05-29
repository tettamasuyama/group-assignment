package group_assignment.employee_management.service.employee;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import group_assignment.employee_management.dto.emoloyee.EmployeeResponseForAdminDto;
import group_assignment.employee_management.dto.emoloyee.EmployeeResponseForEmployeeDto;
import group_assignment.employee_management.entity.Employee;
import group_assignment.employee_management.entity.User;
import group_assignment.employee_management.exception.NotFoundException;
import group_assignment.employee_management.repository.employee.EmployeeRepository;
import group_assignment.employee_management.repository.user.UserRepository;

@Service
public class EmployeeService {
  private final EmployeeRepository employeeRepository;
  private final UserRepository userRepository;

  public EmployeeService(EmployeeRepository employeeRepository, UserRepository userRepository) {
    this.employeeRepository = employeeRepository;
    this.userRepository = userRepository;
  }

  public List<?> findAll() {
    // 認証情報からユーザー情報取得
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    Long userId = (Long) authentication.getPrincipal();

    User user = userRepository.findById(userId)
      .orElseThrow(() -> new NotFoundException(null));

    // employees取得
    List<Employee> employees = employeeRepository.findAll();

    // ユーザーのroleによってレスポンス形式を振り分け
    switch (user.getRole()) {
      case ADMIN:
        return employees.stream()
          .map(employee -> new EmployeeResponseForAdminDto(
            employee.getEmployeeNumber(),
            employee.getName(),
            employee.getEmail(),
            employee.getDepartment(),
            employee.getPosition(),
            employee.getHireDate(),
            employee.getStatus()
          ))
          .toList();

      case EMPLOYEE:
        return employees.stream()
          .map(employee -> new EmployeeResponseForEmployeeDto(
            employee.getName(),
            employee.getEmail()
          ))
          .toList();

      default:
        throw new IllegalStateException();
    }
  }
}
