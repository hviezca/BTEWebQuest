package com.btewebquest.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("BOOKING_REQUEST")
public class BookingEntity {
    @Id
    @Column("BOOKING_ID")
    private int booking_id;

    @Column("DATE")
    private Date date;

    @Column("EVENT_ID")
    private int event_id;

    @Column("MESSAGE_ID")
    private int message_id;

    public BookingEntity() {}

    public BookingEntity(int booking_id, Date date, int event_id, int message_id) {
        this.booking_id = booking_id;
        this.date = date;
        this.event_id = event_id;
        this.message_id = message_id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }
}
