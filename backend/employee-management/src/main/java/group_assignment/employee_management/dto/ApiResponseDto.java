package group_assignment.employee_management.dto;

import java.util.List;

public class ApiResponseDto<T> {
  private  String status;
  private  List<T> data;
  private  String message;

  public ApiResponseDto(String status, List<T> data, String message) {
    this.status = status;
    this.data = data;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public List<T> getData() {
    return data;
  }

  public String getMessage() {
    return message;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  
  public void setData(List<T> data) {
    this.data = data;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  


}