package group_assignment.employee_management.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import group_assignment.employee_management.dto.common.ApiResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AuthException.class)
  public ResponseEntity<ApiResponseDto<Void>> handleAuthException(AuthException e) {
    ApiResponseDto<Void> res = new ApiResponseDto<Void>(
      "ERROR",
      null,
      e.getMessage());

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ApiResponseDto<Void>> handleValidationException(ValidationException e) {
    ApiResponseDto<Void> res = new ApiResponseDto<Void>(
      "ERROR", 
      null, 
      e.getMessage()
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
  }

  @ExceptionHandler(FileException.class)
  public ResponseEntity<ApiResponseDto<Void>> handleFileException(FileException e) {
    ApiResponseDto<Void> res = new ApiResponseDto<Void>(
      "ERROR", 
      null, 
      e.getMessage()
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiResponseDto<Void>>
    handleDataIntegrityViolationException(
        DataIntegrityViolationException e
    ) {
      ApiResponseDto<Void> res = new ApiResponseDto<Void>("ERROR", null, null);
      return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
  }
  
  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ApiResponseDto<Void>>handleConflictException(ConflictException e) {
    ApiResponseDto<Void> res = new ApiResponseDto<Void>(
      "ERROR", 
      null, 
      e.getMessage()
    );
    return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiResponseDto<Void>> handleNotFoundException(NotFoundException e) {
    ApiResponseDto<Void> res = new ApiResponseDto<Void>(
      "ERROR", 
      null, 
      e.getMessage()
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponseDto<Void>>
  handleException(Exception e) {
    ApiResponseDto<Void> res = new ApiResponseDto<>(null, null, "サーバーエラーが発生しました。");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
  }
}
