/**
 * BOOKING BUSINESS SERVICE
 * A business service class for booking requests
 * Author @ Hiram Viezca
 */

package com.btewebquest.business;

import com.btewebquest.data.entity.BookingEntity;
import com.btewebquest.data.service.BookingDataService;
import com.btewebquest.model.BookingModel;
import com.btewebquest.model.EventModel;
import com.btewebquest.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingBusinessService {

    @Autowired
    private BookingDataService bookingDataService;

    @Autowired
    private EventBusinessService eventBusinessService;

    @Autowired
    private MessageBusinessService messageBusinessService;

    /**
     * Creates a new booking request entry in the database.
     * @param booking - A BookingModel object.
     * @return True if entry is successful. False if entry failed
     */
    public boolean addBooking(BookingModel booking) {
        System.out.println("Entered Booking Business Service.");
        return bookingDataService.createBooking(booking);
    }

    /**
     * Gets all booking requests from the database.
     * @return A List of all BookingModel objects.
     */
    public List<BookingModel> getBooking()
    {
        // Create a new list to hold booking requests.
        List<BookingModel> bookingModelList = new ArrayList<>();

        // Get all booking requests from database
        List<BookingEntity> bookingEntities = bookingDataService.findAll();

        // Add event and message to each booking request
        for (BookingEntity bookingEntity :
                bookingEntities) {
            BookingModel booking = new BookingModel();

            int eventID = bookingEntity.getEvent_id();
            EventModel event = eventBusinessService.findById(eventID);
            booking.setEvent(event);

            int messageID = bookingEntity.getMessage_id();
            MessageModel message = messageBusinessService.findById(messageID);
            booking.setMessage(message);

            // Add the booking request to the list
            bookingModelList.add(booking);
        }

        // Return list of bookings.
        return bookingModelList;
    }
}
