/**
 * MANAGEMENT CONTROLLER
 * A Controller class for the Booking Management
 * Author @ Hiram Viezca
 */

package com.btewebquest.controller;

import com.btewebquest.business.BookingBusinessService;
import com.btewebquest.business.ContactBusinessService;
import com.btewebquest.business.MessageBusinessService;
import com.btewebquest.business.UserBusinessService;
import com.btewebquest.model.BookingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/BTE")
public class ManagementController {

    @Autowired
    BookingBusinessService service;

    @Autowired
    UserBusinessService userService;

    // For Delete functions. Tables in DB are set to Cascade.
    // Need to delete the last item in the chain for cascade to function.
    @Autowired
    ContactBusinessService contactBusinessService;

    // For Delete functions. Tables in DB are set to Cascade
    // Message is part of BookingModel. Message will be missed by Cascade.
    @Autowired
    MessageBusinessService messageBusinessService;

    /**
     * Gets all Booking Records from database and display the Booking Management page
     * @param model A Model object for transferring data to the View
     * @return booking-management.html
     */
    @GetMapping("/booking-management")
    public String bookingManagement(Model model) {
        // Get all booking records from database.
        List<BookingModel> bookings = service.getBooking();

        model.addAttribute("title", "Booking Management");
        model.addAttribute("bookings", bookings);
        return "admin/booking/booking-management";
    }

    /**
     * Return booking data as ResponseEntity
     *
     * @param id ID of desired BookingModel
     * @return Booking Management page
     */
    @GetMapping("/booking/json/{id}")
    public ResponseEntity<?> getBookingJson(@PathVariable("id") int id)
    {
        System.out.println("Entered getBookingJson(): ID = " + id);
        try
        {
            // Get Booking from database
            BookingModel booking = service.findById(id);

            // If Booking is null, send fail, else send BookingModel
            if (booking == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(booking, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete BookingModel from database
     *
     * @param id ID of desired BookingModel to delete
     * @param model Booking Model
     * @return Booking Management Page
     */
    @DeleteMapping("/booking/delete/{id}")
    public String deleteBooking(@PathVariable int id, Model model) {
        // Get Booking from database
        BookingModel booking = service.findById(id);

        /*
         * Since tables in DB are set to CASCADE ON DELETE, the deletion
         * has to begin with the last element in the chain. We begin deleting
         * a BookingEntity by deleting the ContactEntity associated with the BookingEntity.
         * This way, the cascading deletions can work as intended all the way up to the
         * BookingEntity.
         */
        contactBusinessService.deleteContact(booking.getEvent().getVenue().getContact());

        /*
         * Since a BookingEntity is made up of an EventEntity and a
         * MessageEntity, unfortunately, the cascading deletions will miss
         * the MessageEntity associated with the BookingEntity. The MessageEntity
         * has to be deleted manually. This decision was made to have as many elements
         * deleted by the cascade.
         */
        messageBusinessService.deleteMessage(booking.getMessage());

        // Get List of all Bookings in database
        List<BookingModel> bookings = service.getBooking();

        // Setup information for View Model
        model.addAttribute("title", "Booking Management");
        model.addAttribute("bookings", bookings);

        return "admin/booking/booking-management :: #bookingTable";
    }
}
