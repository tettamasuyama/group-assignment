package group_assignment.employee_management.dto.common;

public class ApiResponseDto<T> {
  private String status;
  private T data;

  public ApiResponseDto(String status, T data) {
    this.status = status;
    this.data = data;
  }

  public String getStatus() { 
    return status; 
  }
  public T getData() { 
    return data; 
  }
}
