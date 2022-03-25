package com.btewebquest.business;

import com.btewebquest.data.entity.MessageEntity;
import com.btewebquest.data.service.MessageDataService;
import com.btewebquest.model.BookingModel;
import com.btewebquest.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageBusinessService {

    @Autowired
    private MessageDataService service;

    public MessageEntity addMessage(MessageModel message)
    {
        MessageEntity messageEntity = new MessageEntity(message.getMessage_id(),
                                                        message.getSubject(),
                                                        message.getMessage(),
                                                        message.isHas_reply());
        return service.createMessage(messageEntity);
    }
}
