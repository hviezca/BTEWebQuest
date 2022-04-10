package com.btewebquest.controller;

import com.btewebquest.business.ContactRequestBusinessService;
import com.btewebquest.model.BookingModel;
import com.btewebquest.model.ContactRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Controller
public class ContactUsFormController {

    @Autowired
    ContactRequestBusinessService service;

    @Autowired
    JavaMailSender javaMailSender;

    /**
     * Display the ContactUs form.
     *
     * @param model - A Model object to hold object data from the HTML page.
     * @return - The ContactUs form.
     */
    @GetMapping("/contactUs")
    public String getContactUsForm(Model model)
    {
        model.addAttribute("title", "Contact Break the Earth");
        model.addAttribute("contactRequest", new ContactRequestModel());

        return "contact-us-form";
    }

    /**
     * Send a ContactRequestModel to the ContactRequestBusinessService for saving to the database
     *
     * @param contactRequest - A ContactRequestModel
     * @return - A ResponseEntity<> with the Http Status. Either OK, NOT_FOUND, or INTERNAL_SERVER_ERROR
     */
    @PostMapping("/contactSubmit")
    public ResponseEntity<?> contactSubmit(@ModelAttribute ContactRequestModel contactRequest)
    {
        ////////////// TESTING ////////////////
        /*System.out.println(contactRequest.getMessage().getSubject());
        System.out.println(contactRequest.getMessage().getMessage());
        System.out.println(contactRequest.getContact().getContact_name());
        System.out.println(contactRequest.getContact().getContact_phone());
        System.out.println(contactRequest.getContact().getContact_email());*/

        try {
            // Save Booking
            if(service.addContactRequest(contactRequest))
            {
                notifyOfContactRequest(contactRequest);
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
                //return "admin/success";
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //return null;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void notifyOfContactRequest(ContactRequestModel contactRequest) throws MessagingException {
        String subject = "New Contact Request From: " + contactRequest.getContact().getContact_name();
        String msgBody = "You have a new contact request. Please click " +
                "<html><a href='https://breaktheearth.herokuapp.com/BTE/contact-us-management'>here</a></html> " +
                "to access your booking requests";

        // Set up email to send
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        /////////// FOR TESTING ONLY
        helper.setTo("hiramramirez3@gmail.com");

        //helper.setTo(booking.getEvent().getVenue().getContact().getContact_email());

        helper.setSubject(subject);
        helper.setText(msgBody, msgBody);

        javaMailSender.send(message);
    }
}
