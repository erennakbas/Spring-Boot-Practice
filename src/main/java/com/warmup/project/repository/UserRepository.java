package com.warmup.project.repository;

import com.warmup.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByFirstName(String firstName);
    User findUserByFirstNameAndLastName(String firstName, String lastName);
    boolean existsUserByUsername(String username);
}
