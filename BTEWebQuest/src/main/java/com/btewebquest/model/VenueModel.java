package com.btewebquest.model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class VenueModel {

    @Id
    private int venue_id;

    @NotEmpty
    @Size(min = 4, max = 20, message="Venue name must be between 4 and 20 characters")
    private String venue_name;

    @NotEmpty
    @Size(min = 4, max = 20, message="Address name must be between 4 and 20 characters")
    private String venue_address;

    @NotEmpty
    @Size(min = 4, max = 20, message="City name must be between 4 and 20 characters")
    private String venue_city;

    @NotEmpty
    @Size(min = 2, max = 2, message="Please select a state")
    private String venue_state;

    @NotEmpty
    @Size(min = 5, max = 5, message = "Zip must be 5 digits")
    private String venue_zip;

    private ContactModel contact = new ContactModel();

    public VenueModel(){}

    public VenueModel(int venue_id, String venue_name, String venue_address, String venue_city, String venue_state, String venue_zip, ContactModel contact) {
        this.venue_id = venue_id;
        this.venue_name = venue_name;
        this.venue_address = venue_address;
        this.venue_city = venue_city;
        this.venue_state = venue_state;
        this.venue_zip = venue_zip;
        this.contact = contact;
    }

    public int getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(int venue_id) {
        this.venue_id = venue_id;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public String getVenue_address() {
        return venue_address;
    }

    public void setVenue_address(String venue_address) {
        this.venue_address = venue_address;
    }

    public String getVenue_city() {
        return venue_city;
    }

    public void setVenue_city(String venue_city) {
        this.venue_city = venue_city;
    }

    public String getVenue_state() {
        return venue_state;
    }

    public void setVenue_state(String venue_state) {
        this.venue_state = venue_state;
    }

    public String getVenue_zip() {
        return venue_zip;
    }

    public void setVenue_zip(String venue_zip) {
        this.venue_zip = venue_zip;
    }

    public ContactModel getContact() {
        return contact;
    }

    public void setContact(ContactModel contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "VenueModel{" +
                "venue_id=" + venue_id +
                ", venue_name='" + venue_name + '\'' +
                ", venue_address='" + venue_address + '\'' +
                ", venue_city='" + venue_city + '\'' +
                ", venue_state='" + venue_state + '\'' +
                ", venue_zip='" + venue_zip + '\'' +
                ", contact=" + contact +
                '}';
    }
}
