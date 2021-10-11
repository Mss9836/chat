package com.mareninss.group_chat.dto;

import com.mareninss.group_chat.entity.Message;

public class DtoMapper {

  public static DtoMessage mapToDtoMessage(Message message) {
    DtoMessage dtoMessage = new DtoMessage();
    dtoMessage.setDateTime(message.getDateTime().toString());
    dtoMessage.setUserName(message.getUser().getName());
    dtoMessage.setText(message.getMessage());

    return dtoMessage;
  }
}
