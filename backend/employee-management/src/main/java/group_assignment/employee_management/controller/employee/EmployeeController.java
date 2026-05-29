package group_assignment.employee_management.controller.employee;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group_assignment.employee_management.dto.common.ApiResponseDto;
import group_assignment.employee_management.service.employee.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping
  public ApiResponseDto<List<?>> getEmployees() {
    List<?> employees = employeeService.findAll();

    return new ApiResponseDto<List<?>>("SUCCESS", employees, null);
  }
}
