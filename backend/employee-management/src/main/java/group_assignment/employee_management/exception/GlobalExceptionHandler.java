package group_assignment.employee_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import group_assignment.employee_management.dto.common.ApiResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AuthException.class)
  public ResponseEntity<ApiResponseDto<String>> handleAuthException(
    AuthException e
  ) {
    ApiResponseDto<String> res = new ApiResponseDto<String>("ERROR", e.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
  }
}
