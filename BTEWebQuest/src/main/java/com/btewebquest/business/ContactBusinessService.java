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

    public ContactEntity addContact(ContactModel contact)
    {
        contact.setContact_phone("555-555-5555");

        ContactEntity contactEntity = new ContactEntity(contact.getContact_id(),
                                                        contact.getContact_name(),
                                                        contact.getContact_email(),
                                                        contact.getContact_phone());

        return service.createContact(contactEntity);
    }
}
