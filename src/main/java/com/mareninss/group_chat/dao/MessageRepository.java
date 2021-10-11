package com.mareninss.group_chat.dao;

import com.mareninss.group_chat.entity.Message;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
