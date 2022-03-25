package com.btewebquest.model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class BookingModel {

    @Id
    private int booking_id;

    private Date booking_date;

    private MessageModel message = new MessageModel();

    private EventModel event = new EventModel();

    public BookingModel() {}

    public BookingModel(int booking_id, Date booking_date, MessageModel message, EventModel event) {
        this.booking_id = booking_id;
        this.booking_date = booking_date;
        this.message = message;
        this.event = event;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public EventModel getEvent() {
        return event;
    }

    public MessageModel getMessage() {
        return message;
    }

    public void setMessage(MessageModel message) {
        this.message = message;
    }

    public void setEvent(EventModel event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "BookingModel{" +
                "booking_id=" + booking_id +
                ", booking_date=" + booking_date +
                ", message=" + message +
                ", event=" + event +
                '}';
    }
}
