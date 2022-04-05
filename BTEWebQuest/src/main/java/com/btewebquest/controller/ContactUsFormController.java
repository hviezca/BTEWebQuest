package com.btewebquest.controller;

import com.btewebquest.model.BookingModel;
import com.btewebquest.model.ContactRequestModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactUsFormController {

    @GetMapping("/contactUs")
    public String getContactUsForm(Model model)
    {
        model.addAttribute("title", "Contact Break the Earth");
        model.addAttribute("contactRequest", new ContactRequestModel());

        return "contact-us-form";
    }

    @PostMapping("/contactSubmit")
    public ResponseEntity<?> bookingSubmit(@ModelAttribute ContactRequestModel contactRequest)
    {
        System.out.println("Entered ContactRequestController.");
        ////////////// TESTING ////////////////
        System.out.println(contactRequest.getMessage().getSubject());
        System.out.println(contactRequest.getMessage().getMessage());
        System.out.println(contactRequest.getContact().getContact_name());
        System.out.println(contactRequest.getContact().getContact_phone());
        System.out.println(contactRequest.getContact().getContact_email());


        return new ResponseEntity<>("/contactUs", HttpStatus.OK);
        /*try {
            // Save Booking
            if(bookingService.addBooking(booking))
                return new ResponseEntity<>("/booking", HttpStatus.OK);
                //return "admin/success";
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //return null;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
    }
}
