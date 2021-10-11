package com.mareninss.group_chat.service;

import com.mareninss.group_chat.dto.DtoMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MessageService {

  public Map<String, Boolean> sendMessage(String message);

  public List<DtoMessage> getMessagesList();

  public HashMap<Integer, String> getUsersList();
}
