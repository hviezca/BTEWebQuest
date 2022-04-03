/**
 * MESSAGE BUSINESS SERVICE
 * A business service class for Message objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.business;

import com.btewebquest.data.entity.MessageEntity;
import com.btewebquest.data.service.MessageDataService;
import com.btewebquest.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageBusinessService {

    @Autowired
    private MessageDataService service;

    /**
     * Add a Message to the database
     * @param message A MessageModel object
     * @return A MessageEntity object
     */
    public MessageEntity addMessage(MessageModel message)
    {
        MessageEntity messageEntity = new MessageEntity(message.getMessage_id(),
                                                        message.getSubject(),
                                                        message.getMessage(),
                                                        message.isHas_reply());
        return service.createMessage(messageEntity);
    }

    /**
     * Find a MessageModel object by its ID number
     * @param id - int - A MessageModel ID number
     * @return A MessageModel object
     */
    public MessageModel findById(int id)
    {
        MessageModel messageModel = new MessageModel();
        MessageEntity messageEntity = service.findById(id);

        messageModel.setMessage_id(messageEntity.getId());
        messageModel.setSubject(messageEntity.getSubject());
        messageModel.setMessage(messageEntity.getMessage());
        messageModel.setHas_reply(messageEntity.isHas_reply());

        return messageModel;
    }

    public boolean replyToMessage(MessageModel message)
    {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId(message.getMessage_id());
        messageEntity.setSubject(message.getSubject());
        messageEntity.setMessage(message.getMessage());
        messageEntity.setHas_reply(message.isHas_reply());

        if(service.replyToMessage(messageEntity))
            return true;
        return false;
    }

    public boolean deleteMessage(MessageModel message)
    {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId(message.getMessage_id());
        messageEntity.setSubject(message.getSubject());
        messageEntity.setMessage(message.getMessage());
        messageEntity.setHas_reply(message.isHas_reply());

        if (service.delete(messageEntity))
            return true;
        return false;
    }
}
