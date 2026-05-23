package group_assignment.employee_management.repository.user;

import org.springframework.stereotype.Repository;

import group_assignment.employee_management.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);
}
