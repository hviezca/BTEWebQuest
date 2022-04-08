package com.btewebquest.model;

import javax.persistence.Id;
import java.util.Date;

public class EventModel {

    @Id
    private int event_id;

    private Date event_date;

    private double event_price;

    private boolean all_ages;

    private VenueModel venue = new VenueModel();

    public EventModel() {}

    public EventModel(int event_id, Date event_date, double event_price, boolean all_ages, VenueModel venue) {
        this.event_id = event_id;
        this.event_date = event_date;
        this.event_price = event_price;
        this.all_ages = all_ages;
        this.venue = venue;
    }

    public EventModel(int event_id, Date event_date, double event_price, boolean all_ages, int venue_id) {
        this.event_id = event_id;
        this.event_date = event_date;
        this.event_price = event_price;
        this.all_ages = all_ages;
        this.venue = new VenueModel();
        this.venue.setVenue_id(venue_id);
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public double getEvent_price() {
        return event_price;
    }

    public void setEvent_price(double event_price) {
        this.event_price = event_price;
    }

    public boolean isAll_ages() {
        return all_ages;
    }

    public void setAll_ages(boolean all_ages) {
        this.all_ages = all_ages;
    }

    public VenueModel getVenue() {
        return venue;
    }

    public void setVenue(VenueModel venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "EventModel{" +
                "event_id=" + event_id +
                ", event_date=" + event_date +
                ", event_price=" + event_price +
                ", all_ages=" + all_ages +
                ", venue=" + venue +
                '}';
    }
}
