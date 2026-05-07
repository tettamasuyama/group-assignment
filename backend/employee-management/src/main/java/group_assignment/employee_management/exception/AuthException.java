package group_assignment.employee_management.exception;

public class AuthException extends RuntimeException  {
  
  public AuthException(String message) {
    super(message);
  }
}