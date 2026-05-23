package group_assignment.employee_management.repository.employee;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import group_assignment.employee_management.entity.Department;
import group_assignment.employee_management.entity.Employee;
import group_assignment.employee_management.entity.Position;
import group_assignment.employee_management.entity.Status;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Optional<Employee> findByEmail(String email);

  void deleteByEmployeeNumber(String employeeNumber);

  @Modifying
  @Query(
    value = """
      INSERT INTO employees (
        employee_number,
        name,
        email,
        department,
        position,
        hire_date,
        status
      )
      VALUES (
        :employeeNumber,
        :name,
        :email,
        :department,
        :position,
        :hireDate,
        :status
      )
      ON CONFLICT(employee_number)

      DO UPDATE SET
        name = EXCLUDED.name,
        email = EXCLUDED.email,
        department = EXCLUDED.department,
        position = EXCLUDED.position,
        hireDate = EXCLUDED.hireDate,
        status = EXCLUDED.status

        RETURNING *
    """,
    nativeQuery = true
  ) 
    Employee upsert(
      @Param("employeeNumber") String employeeNumber,
      @Param("name") String name,
      @Param("email") String email,
      @Param("department") Department department,
      @Param("position") Position position,
      @Param("hireDate") LocalDate hireDate,
      @Param("status") Status status
    );

  @Modifying
  @Query(
    value = """
        UPDATE employees
        SET
          name = :name,
          email = :email
        WHERE employee_number = :employeeNumber
        RETURNING * 
        """,
        nativeQuery = true
  )
    Employee update(
      @Param("employeeNumber") String employeeNumber,
      @Param("name") String name,
      @Param("email") String email
    );
}

