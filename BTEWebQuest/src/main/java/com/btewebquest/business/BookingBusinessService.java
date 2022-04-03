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
import com.btewebquest.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

            booking.setBooking_id(bookingEntity.getBooking_id());
            booking.setBooking_date(bookingEntity.getDate());
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

    public BookingModel findById(int id)
    {
        BookingModel bookingModel = new BookingModel();
        BookingEntity booking = bookingDataService.findById(id);

        bookingModel.setBooking_id(booking.getBooking_id());
        bookingModel.setBooking_date(booking.getDate());
        bookingModel.setEvent(eventBusinessService.findById(booking.getEvent_id()));
        bookingModel.setMessage(messageBusinessService.findById(booking.getMessage_id()));

        return bookingModel;
    }

    /**
     * Delete a booking entity from the database
     *
     * @param booking - A BookingEntity Object
     * @return - Boolean
     */
    public boolean deleteBooking(BookingModel booking)
    {
        // Get User from database
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setBooking_id(booking.getBooking_id());
        bookingEntity.setDate(booking.getBooking_date());
        bookingEntity.setEvent_id(booking.getEvent().getEvent_id());
        bookingEntity.setMessage_id(booking.getMessage().getMessage_id());

        // Delete User from database
        if (bookingDataService.delete(bookingEntity))
            return true;
        return false;
    }
}
