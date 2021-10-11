package com.mareninss.group_chat.service;

import com.mareninss.group_chat.dao.MessageRepository;
import com.mareninss.group_chat.dao.UserRepository;
import com.mareninss.group_chat.dto.DtoMapper;
import com.mareninss.group_chat.dto.DtoMessage;
import com.mareninss.group_chat.entity.Message;
import com.mareninss.group_chat.entity.User;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@Service
public class MessageServiceImpl implements MessageService {

  @Autowired
  private MessageRepository messageRepository;
  @Autowired
  private UserRepository userRepository;

  @Override
  public Map<String, Boolean> sendMessage(String message) {
    Map<String, Boolean> response = new HashMap<>();
    String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
    User user = userRepository.findBySessionId(sessionId).get();

    if (Strings.isNotEmpty(message)) {
      LocalDateTime dateTime = LocalDateTime.now();
      Message newMessage = new Message();

      newMessage.setMessage(message);
      newMessage.setDateTime(dateTime);
      newMessage.setUser(user);
      messageRepository.saveAndFlush(newMessage);
      response.put("result", true);

      return response;
    } else {
      response.put("result", false);
      return response;
    }
  }

  @Override
  public List<DtoMessage> getMessagesList() {
    return messageRepository
        .findAll(Sort.by(Direction.ASC, "dateTime"))
        .stream()
        .map(message -> DtoMapper.mapToDtoMessage(message))
        .collect(Collectors.toList());
  }

  @Override
  public HashMap<Integer, String> getUsersList() {
    return null;
  }
}
