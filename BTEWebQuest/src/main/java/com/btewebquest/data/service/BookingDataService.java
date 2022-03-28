/**
 * BOOKING DATA SERVICE
 * A Data Access Service class for booking requests
 *
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.service;

import com.btewebquest.business.*;
import com.btewebquest.data.entity.*;
import com.btewebquest.data.repository.BookingRepository;
import com.btewebquest.model.BookingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class BookingDataService implements DataAccessInterface<BookingEntity>{

    @Autowired
    private ContactBusinessService contactBusinessService;

    @Autowired
    private MessageBusinessService messageBusinessService;

    @Autowired
    private VenueBusinessService venueBusinessService;

    @Autowired
    private EventBusinessService eventBusinessService;

    private BookingRepository bookingRepository;
    public BookingDataService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    /**
     * Get all Booking Entity objects from the database
     * @return A List of BookingEntity objects
     */
    @Override
    public List<BookingEntity> findAll() {

        return (List<BookingEntity>) bookingRepository.findAll();
    }

    /**
     * Get a BookingEntity object by its id number
     * @param id id number of a BookingEntity
     * @return the BookingEntity object
     */
    @Override
    public BookingEntity findById(int id) {
        return null;
    }

    @Override
    public boolean create(BookingEntity bookingEntity) {

        BookingEntity savedBookingEntity = bookingRepository.save(bookingEntity);

        if (savedBookingEntity.getBooking_id() != 0)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(BookingEntity bookingEntity) {
        return false;
    }

    @Override
    public boolean delete(BookingEntity bookingEntity) {
        return false;
    }

    /**
     * Create a booking request entry in the database.
     * @param booking - A BookingModel object.
     * @return 
     */
    @Transactional
    public boolean createBooking(BookingModel booking)
    {
        // Save Contact to DB
        ContactEntity contactEntity = contactBusinessService.addContact(booking.getEvent().getVenue().getContact());

        // Set Contact_Id in VenueModel to the value of Contact Entity from the DB
        booking.getEvent().getVenue().getContact().setContact_id(contactEntity.getId());

        // Save Venue
        VenueEntity venueEntity = venueBusinessService.addVenue(booking.getEvent().getVenue());

        // Set Venue_Id in EventModel to the value of Venue Entity from the DB
        booking.getEvent().getVenue().setVenue_id(venueEntity.getId());

        // Save Event
        EventEntity eventEntity = eventBusinessService.addEvent(booking.getEvent());

        // Save Message
        MessageEntity messageEntity = messageBusinessService.addMessage(booking.getMessage());

        // Set Booking Date to Today
        booking.setBooking_date(new Date());

        // Set Event_Id in BookingModel to the value of Event Entity from the DB
        booking.getEvent().setEvent_id(eventEntity.getId());

        // Set Message_Id in BookingModel to the value of Message Entity from the DB
        booking.getMessage().setMessage_id(messageEntity.getId());

        BookingEntity bookingEntity = new BookingEntity(booking.getBooking_id(), booking.getBooking_date(), booking.getEvent().getEvent_id(), booking.getMessage().getMessage_id());

        BookingEntity savedBookingEntity = bookingRepository.save(bookingEntity);

        if (savedBookingEntity.getBooking_id() > 0)
            return true;

        return false;
    }
}
