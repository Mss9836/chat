package com.mareninss.group_chat.dao;

import com.mareninss.group_chat.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findBySessionId(String sessionId);
}
