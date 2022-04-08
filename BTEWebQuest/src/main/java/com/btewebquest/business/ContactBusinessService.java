/**
 * CONTACT BUSINESS SERVICE
 * A business service class for Contact Objects
 * Author @ Hiram Viezca
 */


package com.btewebquest.business;

import com.btewebquest.data.entity.ContactEntity;
import com.btewebquest.data.service.ContactDataService;
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
        ContactEntity contactEntity = new ContactEntity(contact.getContact_id(),
                                                        contact.getContact_name(),
                                                         contact.getContact_email(),
                                                        contact.getContact_phone());

        return service.createContact(contactEntity);
    }

    /**
     * Create a Contact in the database with a ContactModel as return
     *
     * @param contact ContactModel to be created
     * @return A ContactModel
     */
    public ContactModel createContact(ContactModel contact)
    {
        ContactEntity contactEntity = new ContactEntity(contact.getContact_id(),
                contact.getContact_name(),
                contact.getContact_email(),
                contact.getContact_phone());

        contactEntity = service.createContact(contactEntity);

        return new ContactModel(contactEntity.getId(), contactEntity.getName(),contactEntity.getEmail(), contactEntity.getPhone());
    }

    /**
     * Update a Contact within the database
     *
     * @param contact ContactModel to be updated
     * @return Boolean indicating operation success
     */
    public boolean updateContact(ContactModel contact)
    {
        ContactEntity contactEntity = new ContactEntity(contact.getContact_id(),
                contact.getContact_name(),
                contact.getContact_email(),
                contact.getContact_phone());

        return service.update(contactEntity);
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

    /**
     * Delete a Contact from database
     *
     * @param contact ContactModel to be deleted
     * @return boolean indicating operation success
     */
    public boolean deleteContact(ContactModel contact)
    {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setId(contact.getContact_id());
        contactEntity.setName(contact.getContact_name());
        contactEntity.setEmail(contact.getContact_email());
        contactEntity.setPhone(contact.getContact_phone());

        return service.delete(contactEntity);
    }
}
