package com.mareninss.group_chat.service;

import java.util.Map;

public interface UserService {

  public Map<String, Boolean> isAuthorized();

  public Map<String, Boolean> auth(String name);

}
