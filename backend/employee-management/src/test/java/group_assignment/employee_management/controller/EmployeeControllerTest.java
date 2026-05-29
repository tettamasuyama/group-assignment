package group_assignment.employee_management.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import group_assignment.employee_management.controller.employee.EmployeeController;
import group_assignment.employee_management.dto.common.ApiResponseDto;
import group_assignment.employee_management.dto.emoloyee.EmployeeResponseForAdminDto;
import group_assignment.employee_management.entity.Department;
import group_assignment.employee_management.entity.Position;
import group_assignment.employee_management.entity.Status;
import group_assignment.employee_management.service.employee.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

  @Mock EmployeeService employeeService;

  @InjectMocks EmployeeController employeeController;

  @Test void getEmployees_成功すればADMIN用のemployeesリストを取得してApiResponseDtoを返す() {

    EmployeeResponseForAdminDto dto = new EmployeeResponseForAdminDto(
      "00001", 
      "テスト", 
      "test@example.com",
      Department.GENERAL,
      Position.CEO, 
      LocalDate.of(2000, 1, 1),
      Status.ACTIVE
    );

    List<?> employees = List.of(dto);

    // mock
    when(employeeService.findAll())
      .thenReturn((List)employees);

    // controller実行
    ApiResponseDto<List<?>> result = employeeController.getEmployees();

    //検証
    assertEquals("SUCCESS", result.getStatus());

    EmployeeResponseForAdminDto item = (EmployeeResponseForAdminDto) result.getData().get(0);

    assertEquals("00001" ,item.getEmployeeNumber());
    assertEquals("テスト" ,item.getName());
    assertEquals("test@example.com" ,item.getEmail());
    assertEquals(Department.GENERAL ,item.getDepartment());
    assertEquals(Position.CEO ,item.getPosition());
    assertEquals(LocalDate.of(2000, 1, 1) ,item.getHireDate());
    assertEquals(Status.ACTIVE ,item.getStatus());
  }
}
