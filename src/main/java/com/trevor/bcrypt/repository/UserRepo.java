package com.trevor.bcrypt.repository;

import com.trevor.bcrypt.models.UserName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserName, Long> {
    UserName findByUsername(String username);
}
