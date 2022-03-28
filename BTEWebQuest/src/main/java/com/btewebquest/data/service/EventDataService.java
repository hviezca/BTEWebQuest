/**
 * EVENT DATA SERVICE
 * An Data Service class for Event objects
 * Author @ Hiram Viezca
 */


package com.btewebquest.data.service;

import com.btewebquest.data.entity.EventEntity;
import com.btewebquest.data.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventDataService implements DataAccessInterface<EventEntity> {

    private EventRepository eventRepository;

    public EventDataService(EventRepository eventRepository)
    {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventEntity> findAll() {
        return null;
    }

    /**
     * Gets an Event object from the database by its ID number
     * @param id The ID number of Event Object
     * @return An Event Entity object
     */
    @Override
    public EventEntity findById(int id)
    {
        Optional<EventEntity> event = eventRepository.findById((long) id);
        return event.get();
    }

    /**
     * Save an Event Object to the database
     * @param eventEntity An EventEntity object
     * @return True if successful. False if not successful
     */
    @Override
    public boolean create(EventEntity eventEntity) {
        EventEntity savedEventEntity = eventRepository.save(eventEntity);

        if (savedEventEntity.getId() != 0)
        {
            System.out.println("The Event ID is: " + savedEventEntity.getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean update(EventEntity eventEntity) {
        return false;
    }

    @Override
    public boolean delete(EventEntity eventEntity) {
        return false;
    }

    /**
     * Save an Event Object to the database
     * @param eventEntity Ab EventEntity object
     * @return A EventEntity object with its ID number from the database
     */
    public EventEntity createEvent(EventEntity eventEntity)
    {
        return eventRepository.save(eventEntity);
    }
}
