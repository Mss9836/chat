package com.mareninss.group_chat.service;

import com.mareninss.group_chat.dao.UserRepository;
import com.mareninss.group_chat.entity.Message;
import com.mareninss.group_chat.entity.User;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public Map<String, Boolean> isAuthorized() {

    Map<String, Boolean> response = new HashMap();
    String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
    Optional<User> user = userRepository.findBySessionId(sessionId);
    response.put("result", user.isPresent());
    return response;
  }

  @Override
  public Map<String, Boolean> auth(String name) {
    Map<String, Boolean> response = new HashMap();
    if (!Strings.isNotEmpty(name)) {
      log.warn("User name null or empty!");
      response.put("result", false);
    }
    String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
    User user = new User();

    user.setName(name);
    user.setSessionId(sessionId);
    userRepository.save(user);
    response.put("result", true);

    return response;
  }
}
