package group_assignment.employee_management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import group_assignment.employee_management.dto.emoloyee.EmployeeResponseForAdminDto;
import group_assignment.employee_management.dto.emoloyee.EmployeeResponseForEmployeeDto;
import group_assignment.employee_management.entity.Department;
import group_assignment.employee_management.entity.Employee;
import group_assignment.employee_management.entity.Position;
import group_assignment.employee_management.entity.Role;
import group_assignment.employee_management.entity.Status;
import group_assignment.employee_management.entity.User;
import group_assignment.employee_management.repository.employee.EmployeeRepository;
import group_assignment.employee_management.repository.user.UserRepository;
import group_assignment.employee_management.service.employee.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
  @Mock UserRepository userRepository;
  @Mock EmployeeRepository employeeRepository;
  @Mock SecurityContext securityContext;
  @InjectMocks EmployeeService employeeService;

  @Test void RoleがADMINならListEmployeeResponseForAdminDtoを返す() {
    // ユーザー
    Long userId = 1L;
    User user = new User("test@example.com", "password123", Role.ADMIN);

    // 社員リスト
    Employee employee = new Employee(
      "00001",
      "テスト", 
      "test@exapmle.com", 
      Department.GENERAL, 
      Position.CEO, 
      LocalDate.of(2000,1,1), 
      Status.ACTIVE
    );
    List<Employee> employees = List.of(employee);

    // 認証情報
    Authentication authentication = new UsernamePasswordAuthenticationToken(
      userId,
      null
    );

    // mock
    when(securityContext.getAuthentication())
      .thenReturn(authentication);

    when(userRepository.findById(userId))
      .thenReturn(Optional.of(user));

    when(employeeRepository.findAll())
      .thenReturn(employees);

    // static mock
    try(MockedStatic<SecurityContextHolder> mocked = Mockito.mockStatic(SecurityContextHolder.class)) {
      mocked.when(SecurityContextHolder::getContext)
        .thenReturn(securityContext);

      // service実行
      List<?> result = employeeService.findAll();

      //取得した情報確認
      assertEquals(1, result.size());

      EmployeeResponseForAdminDto dto = (EmployeeResponseForAdminDto)result.get(0);

      assertEquals("00001", dto.getEmployeeNumber());
      assertEquals("テスト", dto.getName());
      assertEquals("test@exapmle.com", dto.getEmail());
      assertEquals(Department.GENERAL, dto.getDepartment());
      assertEquals(Position.CEO, dto.getPosition());
      assertEquals(LocalDate.of(2000, 1, 1), dto.getHireDate());
      assertEquals(Status.ACTIVE, dto.getStatus());
    }
  }

  @Test void RoleがEMPLOYEEならListEmployeeResponseForEmployeeDtoを返す() {
    // ユーザー
    Long userId = 1L;
    User user = new User("test@example.com", "password123", Role.EMPLOYEE);

    // 社員リスト
    Employee employee = new Employee(
      "00001",
      "テスト", 
      "test@exapmle.com", 
      Department.GENERAL, 
      Position.CEO, 
      LocalDate.of(2000,1,1), 
      Status.ACTIVE
    );
    List<Employee> employees = List.of(employee);

    // 認証情報
    Authentication authentication = new UsernamePasswordAuthenticationToken(
      userId,
      null
    );

    // mock
    when(securityContext.getAuthentication())
      .thenReturn(authentication);

    when(userRepository.findById(userId))
      .thenReturn(Optional.of(user));

    when(employeeRepository.findAll())
      .thenReturn(employees);

    // static mock
    try(MockedStatic<SecurityContextHolder> mocked = Mockito.mockStatic(SecurityContextHolder.class)) {
      mocked.when(SecurityContextHolder::getContext)
        .thenReturn(securityContext);

      // service実行
      List<?> result = employeeService.findAll();

      //検証
      assertEquals(1, result.size());

      EmployeeResponseForEmployeeDto dto = (EmployeeResponseForEmployeeDto)result.get(0);

      assertEquals("テスト", dto.getName());
      assertEquals("test@exapmle.com", dto.getEmail());
    }
  }
}
