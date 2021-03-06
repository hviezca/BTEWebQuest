/**
 * CONTACT DATA SERVICE
 * An Data Service class for Contact objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.service;

import com.btewebquest.data.entity.ContactEntity;
import com.btewebquest.data.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactDataService implements DataAccessInterface<ContactEntity> {

    private ContactRepository contactRepository;

    public ContactDataService(ContactRepository contactRepository)
    {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<ContactEntity> findAll() {
        return null;
    }

    /**
     * Find a Contact by ID
     * @param id The ID number of the Contact Object
     * @return A ContactEntity object
     */
    @Override
    public ContactEntity findById(int id)
    {
        Optional<ContactEntity> contact = contactRepository.findById((long) id);
        return contact.get();
    }

    /**
     * Save a Contact object to the database
     * @param contactEntity A ContactEntity object
     * @return True if successful. False if not successful
     */
    @Override
    public boolean create(ContactEntity contactEntity) {
        ContactEntity savedContactEntity = contactRepository.save(contactEntity);

        if (savedContactEntity.getId() != 0)
        {
            System.out.println("The Contact ID is: " + savedContactEntity.getId());
            return true;
        }
        return false;
    }

    /**
     * Create new Contact and return ID
     *
     * @param contactEntity ContactEntity to be created
     * @return New ContactEntity
     */
    public int createReturnId(ContactEntity contactEntity) {
        return contactRepository.save(contactEntity).getId();
    }

    /**
     * Update Contact in database
     *
     * @param contactEntity ContactEntity to be updated
     * @return boolean indicating operation success
     */
    @Override
    public boolean update(ContactEntity contactEntity) {

        try
        {
            // Attempt to update Contact in database
           contactRepository.save(contactEntity);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Delete Contact in database
     *
     * @param contactEntity ContactEntity to be deleted
     * @return boolean indicating success
     */
    @Override
    public boolean delete(ContactEntity contactEntity) {

        contactRepository.delete(contactEntity);
        return true;
    }

    /**
     * Save a Contact object to the database
     * @param contactEntity A ContactEntity object
     * @return A ContactEntity object with its ID number from the database
     */
    public ContactEntity createContact(ContactEntity contactEntity)
    {
        return contactRepository.save(contactEntity);
    }
}
