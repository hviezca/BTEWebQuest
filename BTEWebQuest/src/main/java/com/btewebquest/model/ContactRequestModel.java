package com.btewebquest.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class ContactRequestModel {

    @Id
    private int contact_request_id;

    private Date date;

    private ContactModel contact;

    private MessageModel message;

    public ContactRequestModel() {}

    public ContactRequestModel(int contact_request_id, Date date, ContactModel contact, MessageModel message) {
        this.contact_request_id = contact_request_id;
        this.date = date;
        this.contact = contact;
        this.message = message;
    }

    public int getContact_request_id() {
        return contact_request_id;
    }

    public void setContact_request_id(int contact_request_id) {
        this.contact_request_id = contact_request_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ContactModel getContact() {
        return contact;
    }

    public void setContact(ContactModel contact) {
        this.contact = contact;
    }

    public MessageModel getMessage() {
        return message;
    }

    public void setMessage(MessageModel message) {
        this.message = message;
    }
}
