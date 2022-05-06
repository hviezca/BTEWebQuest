/**
 * EVENT DATA SERVICE
 * An Data Service class for Event objects
 * Author @ Hiram Viezca
 */


package com.btewebquest.data.service;

import com.btewebquest.data.entity.EventEntity;
import com.btewebquest.data.repository.EventRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventDataService implements DataAccessInterface<EventEntity> {

    private EventRepository eventRepository;

    public EventDataService(EventRepository eventRepository)
    {
        this.eventRepository = eventRepository;
    }

    /**
     * Find all events and sort by DATE
     *
      * @return List of EventEntity sorted by DATE
     */
    public List<EventEntity> findAllOrderByDate()
    {
        return (List<EventEntity>) eventRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }

    /**
     * Find all events and sort by DATE
     *
     * @return List of EventEntity sorted by DATE
     */
    public List<EventEntity> findAllBooked()
    {
        return (List<EventEntity>) eventRepository.findAllByBookedTrue(Sort.by(Sort.Direction.ASC, "date"));
    }

    /**
     * Return a list of all Events
     *
     * @return List of EventEntity
     */
    @Override
    public List<EventEntity> findAll() {

        // Empty List of EventEntity
        List<EventEntity> events = new ArrayList<EventEntity>();

        try
        {
            // Iterable result set from database
            Iterable<EventEntity> userIterable = eventRepository.findAll();

            // Step through Iterable set and add to EventEntity List
            userIterable.forEach(events::add);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Return List of EventEntity
        return events;
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

    /**
     * Update Event in database
     *
     * @param eventEntity EventEntity to update
     * @return boolean indication operation success
     */
    @Override
    public boolean update(EventEntity eventEntity) {
        try
        {
            // Attempt to update Event in database
            eventRepository.save(eventEntity);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Delete Event in database
     *
     * @param eventEntity EventEntity to be deleted
     * @return boolean indicating operation success
     */
    @Override
    public boolean delete(EventEntity eventEntity) {
        try
        {
            // Attempt to delete EventModel in database
            eventRepository.delete(eventEntity);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
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
