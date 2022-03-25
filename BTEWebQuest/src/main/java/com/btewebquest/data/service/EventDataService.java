package com.btewebquest.data.service;

import com.btewebquest.data.entity.ContactEntity;
import com.btewebquest.data.entity.EventEntity;
import com.btewebquest.data.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public EventEntity findById(int id) {
        return null;
    }

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

    public EventEntity createEvent(EventEntity eventEntity)
    {
        return eventRepository.save(eventEntity);
    }
}
