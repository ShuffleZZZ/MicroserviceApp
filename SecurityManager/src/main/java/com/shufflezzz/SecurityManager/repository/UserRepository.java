package com.shufflezzz.SecurityManager.repository;

import com.shufflezzz.SecurityManager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
