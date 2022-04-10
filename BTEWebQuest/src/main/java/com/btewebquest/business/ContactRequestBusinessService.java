package com.btewebquest.business;

import com.btewebquest.data.entity.ContactRequestEntity;
import com.btewebquest.data.service.ContactRequestDataService;
import com.btewebquest.model.ContactModel;
import com.btewebquest.model.ContactRequestModel;
import com.btewebquest.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactRequestBusinessService
{

    @Autowired
    ContactRequestDataService service;

    @Autowired
    private ContactBusinessService contactBusinessService;

    @Autowired
    private MessageBusinessService messageBusinessService;

    /**
     * Send a ContactRequestEntity to the ContactRequestDataService for saving to the database.
     *
     * @param contactRequest A ContactRequestModel
     * @return - boolean
     */
    public boolean addContactRequest(ContactRequestModel contactRequest)
    {
        /*ContactRequestEntity contactRequestEntity = new ContactRequestEntity(contactRequest.getContact_request_id(),
                                                                             contactRequest.getDate(),
                                                                             contactRequest.getContact().getContact_id(),
                                                                             contactRequest.getMessage().getMessage_id());*/

        return service.addContactRequest(contactRequest);
    }

    /**
     * Get all ContactRequests from the database.
     *
     * @return - A List of ContactRequestModels
     */
    public List<ContactRequestModel> getContactRequests()
    {
        // Create a new ContactRequest List
        List<ContactRequestModel> contactRequestList = new ArrayList<>();

        // Get all ContactRequestEntities from the database
        List<ContactRequestEntity> contactRequestEntities = service.findAll();

        // Convert ContactRequestEntities to ContactRequestModels.
        for (ContactRequestEntity entity:
                contactRequestEntities) {

            // Create a new ContactRequestModel
            ContactRequestModel model = new ContactRequestModel();

            // Set the ID and Date on the ContactRequestModel from the entity
            model.setContact_request_id(entity.getContact_request_id());
            model.setDate(entity.getDate());

            // Get the entity's Message ID and find that message in the database
            // Add the message to the ContactRequestModel
            MessageModel message = messageBusinessService.findById(entity.getMessage_id());
            model.setMessage(message);

            // Get the entity's Contact ID and find that message in the database
            // Add the contact to the ContactRequestModel
            ContactModel contact = contactBusinessService.findById(entity.getPerson_id());
            model.setContact(contact);

            // Add the Complete ContactRequestModel to the ContactRequest List
            contactRequestList.add(model);
        }

        // Return the List of ContactRequestModels
        return contactRequestList;
    }

    public ContactRequestModel findById(int id) {

        ContactRequestEntity entity = service.findById(id);
        ContactModel contactModel = contactBusinessService.findById(entity.getPerson_id());
        MessageModel messageModel = messageBusinessService.findById(entity.getMessage_id());

        return new ContactRequestModel(entity.getContact_request_id(), entity.getDate(), contactModel, messageModel);
    }
}
