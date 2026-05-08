package group_assignment.employee_management.repository.user;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public class UserRepository extends  JpaRepository<User, Long>{
  
}
