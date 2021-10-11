package com.mareninss.group_chat.controller;


import com.mareninss.group_chat.dto.DtoMessage;
import com.mareninss.group_chat.entity.Message;
import com.mareninss.group_chat.service.MessageService;
import com.mareninss.group_chat.service.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChatController {

  @Autowired
  private UserService userService;
  @Autowired
  private MessageService messageService;

  @GetMapping("/init")
  public Map<String, Boolean> isAuthorized() {
    return userService.isAuthorized();
  }

  @PostMapping("/auth")
  public Map<String, Boolean> auth(@RequestParam String name) {
    return userService.auth(name);
  }

  @PostMapping("/message")
  public Map<String, Boolean> sendMessage(@RequestParam String message) {
    return messageService.sendMessage(message);
  }

  @GetMapping("/message")
  public List<DtoMessage> getMessagesList() {
    return messageService.getMessagesList();
  }

  @GetMapping("/user")
  public HashMap<Integer, String> getUsersList() {
    return null;
  }
}
