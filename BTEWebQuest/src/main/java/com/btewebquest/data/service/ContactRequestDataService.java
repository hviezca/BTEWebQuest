package com.btewebquest.data.service;

import com.btewebquest.business.ContactBusinessService;
import com.btewebquest.business.MessageBusinessService;
import com.btewebquest.data.entity.ContactEntity;
import com.btewebquest.data.entity.ContactRequestEntity;
import com.btewebquest.data.entity.MessageEntity;
import com.btewebquest.data.repository.ContactRequestRepository;
import com.btewebquest.model.ContactModel;
import com.btewebquest.model.ContactRequestModel;
import com.btewebquest.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContactRequestDataService implements DataAccessInterface<ContactRequestEntity>
{
    @Autowired
    private ContactRequestRepository contactRequestRepository;

    @Autowired
    private ContactBusinessService contactBusinessService;

    @Autowired
    private MessageBusinessService messageBusinessService;

    @Override
    public List<ContactRequestEntity> findAll() {
        Iterable<ContactRequestEntity> entities = contactRequestRepository.findAll();
        return (List<ContactRequestEntity>) entities;
    }

    @Override
    public ContactRequestEntity findById(int id) {

        Optional<ContactRequestEntity> entity = contactRequestRepository.findById((long) id);

        return entity.get();
    }

    /**
     * Save a ContactRequestEntity to the database.
     *
     * @param contactRequestEntity - A ContactRequestEntity
     * @return True or Throw SQL Exception.
     */
    @Override
    public boolean create(ContactRequestEntity contactRequestEntity) {

        contactRequestRepository.save(contactRequestEntity);
        return true;
    }

    @Override
    public boolean update(ContactRequestEntity contactRequestEntity) {
        return false;
    }

    @Override
    public boolean delete(ContactRequestEntity contactRequestEntity) {
        return false;
    }

    @Transactional
    public boolean addContactRequest(ContactRequestModel contactRequest) {

        // Save Message
        MessageEntity message = messageBusinessService.addMessage(contactRequest.getMessage());

        // Save Contact
        ContactEntity contact = contactBusinessService.addContact(contactRequest.getContact());

        Date date = new Date();
        // Construct ContactRequestEntity
        ContactRequestEntity contactRequestEntity = new ContactRequestEntity(contactRequest.getContact_request_id(),
                                                        date,
                                                        contact.getId(),
                                                        message.getId());
        // Save ContactRequestEntity
        this.create(contactRequestEntity);
        return true;
    }
}
