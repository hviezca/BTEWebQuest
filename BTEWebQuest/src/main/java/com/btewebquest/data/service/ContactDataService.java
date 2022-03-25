package com.btewebquest.data.service;

import com.btewebquest.data.entity.BookingEntity;
import com.btewebquest.data.entity.ContactEntity;
import com.btewebquest.data.repository.BookingRepository;
import com.btewebquest.data.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ContactEntity findById(int id) {
        return null;
    }

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

    @Override
    public boolean update(ContactEntity contactEntity) {
        return false;
    }

    @Override
    public boolean delete(ContactEntity contactEntity) {
        return false;
    }

    public ContactEntity createContact(ContactEntity contactEntity)
    {
        return contactRepository.save(contactEntity);
    }
}
