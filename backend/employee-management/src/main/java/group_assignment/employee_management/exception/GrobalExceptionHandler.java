package group_assignment.employee_management.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import group_assignment.employee_management.dto.common.ApiResponseDto;

@RestControllerAdvice
public class GrobalExceptionHandler {
  
  @ExceptionHandler(RuntimeException.class)
  public ApiResponseDto<String> handle(RuntimeException e) {
    return new ApiResponseDto<>("ERROR", e.getMessage());
  }
}
