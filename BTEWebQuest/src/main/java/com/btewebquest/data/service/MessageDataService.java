package com.btewebquest.data.service;

import com.btewebquest.data.entity.ContactEntity;
import com.btewebquest.data.entity.MessageEntity;
import com.btewebquest.data.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageDataService implements DataAccessInterface<MessageEntity> {

    private MessageRepository messageRepository;

    public MessageDataService(MessageRepository messageRepository)
    {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageEntity> findAll() {
        return null;
    }

    @Override
    public MessageEntity findById(int id) {
        return null;
    }

    @Override
    public boolean create(MessageEntity messageEntity) {
        MessageEntity savedMessageEntity = messageRepository.save(messageEntity);

        if (savedMessageEntity.getId() != 0)
        {
            System.out.println("The Message ID is: " + savedMessageEntity.getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean update(MessageEntity messageEntity) {
        return false;
    }

    @Override
    public boolean delete(MessageEntity messageEntity) {
        return false;
    }

    public MessageEntity createMessage(MessageEntity messageEntity)
    {
        return messageRepository.save(messageEntity);
    }
}
