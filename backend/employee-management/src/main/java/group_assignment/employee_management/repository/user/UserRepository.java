package group_assignment.employee_management.repository.user;

import org.springframework.stereotype.Repository;

import group_assignment.employee_management.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository 
  extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
  
}
