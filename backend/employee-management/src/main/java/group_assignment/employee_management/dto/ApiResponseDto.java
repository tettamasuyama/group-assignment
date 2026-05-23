package group_assignment.employee_management.dto;
public class ApiResponseDto<T> {
  private  String status;
  private  T data;
  private  String message;

  public ApiResponseDto(String status, T data, String message) {
    this.status = status;
    this.data = data;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public T getData() {
    return data;
  }

  public String getMessage() {
    return message;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  
  public void setData(T data) {
    this.data = data;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  


}