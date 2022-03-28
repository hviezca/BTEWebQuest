/**
 * CONTACT BUSINESS SERVICE
 * A business service class for Contact Objects
 * Author @ Hiram Viezca
 */


package com.btewebquest.business;

import com.btewebquest.data.entity.ContactEntity;
import com.btewebquest.data.service.ContactDataService;
import com.btewebquest.model.BookingModel;
import com.btewebquest.model.ContactModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactBusinessService {

    @Autowired
    ContactDataService service;

    /**
     * Add a Contact to the database
     * @param contact A Contact Model object
     * @return A ContactEntity object
     */
    public ContactEntity addContact(ContactModel contact)
    {
        contact.setContact_phone("555-555-5555");

        ContactEntity contactEntity = new ContactEntity(contact.getContact_id(),
                                                        contact.getContact_name(),
                                                        contact.getContact_email(),
                                                        contact.getContact_phone());

        return service.createContact(contactEntity);
    }

    /**
     * Finds a contact by its ID number
     * @param id The ID number of the Contact onject
     * @return a ContactModel object
     */
    public ContactModel findById(int id)
    {
        ContactModel contactModel = new ContactModel();
        ContactEntity contactEntity = service.findById(id);

        contactModel.setContact_id(contactEntity.getId());
        contactModel.setContact_name(contactEntity.getName());
        contactModel.setContact_email(contactEntity.getEmail());
        contactModel.setContact_phone(contactEntity.getPhone());

        return contactModel;
    }
}
