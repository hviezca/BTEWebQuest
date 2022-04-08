/**
 * EVENT ENTITY
 * An Entity class for Event objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("event")
public class EventEntity {

    @Id
    @Column("EVENT_ID")
    private int id;

    @Column("DATE")
    private Date date;

    @Column("PRICE")
    private double price;

    @Column("ALL_AGES")
    private boolean all_ages;

    @Column("VENUE_ID")
    private int venue_id;

    public EventEntity(){}

    public EventEntity(int id, Date date, double price, boolean all_ages, int venue_id) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.all_ages = all_ages;
        this.venue_id = venue_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAll_ages() {
        return all_ages;
    }

    public void setAll_ages(boolean all_ages) {
        this.all_ages = all_ages;
    }

    public int getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(int venue_id) {
        this.venue_id = venue_id;
    }
}
