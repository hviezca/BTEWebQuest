/**
 * MESSAGE DATA SERVICE
 * An Data Service class for Event objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.service;

import com.btewebquest.data.entity.MessageEntity;
import com.btewebquest.data.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    /**
     * Find a Message object from the database
     * @param id The ID number of Message Object
     * @return A MessageEntity object
     */
    @Override
    public MessageEntity findById(int id)
    {
        Optional<MessageEntity> message = messageRepository.findById((long) id);
        return message.get();
    }
    /**
     * Save a Message object to the database
     * @param messageEntity A MessageEntity object
     * @return True if successful. False if not successful
     */
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

    /**
     * Save a Message object to the database
     * @param messageEntity A MessageEntity object
     * @return A MessageEntity object with its ID number from the database
     */
    public MessageEntity createMessage(MessageEntity messageEntity)
    {
        return messageRepository.save(messageEntity);
    }
}
