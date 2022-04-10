/**
 * VENUE ENTITY
 * An Entity class for Venue objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("venue")
public class VenueEntity
{
    @Id
    @Column("VENUE_ID")
    private int id;

    @Column("VENUE_NAME")
    private String venue_name;

    @Column("VENUE_ADDRESS")
    private String venue_address;

    @Column("VENUE_CITY")
    private String venue_city;

    @Column("VENUE_STATE")
    private String venue_state;

    @Column("VENUE_ZIP")
    private String venue_zip;

    @Column("CONTACT_ID")
    private int contact_id;

    public VenueEntity(){}

    public VenueEntity(int id, String venue_name, String venue_address, String venue_city, String venue_state, String venue_zip, int contact_id) {
        this.id = id;
        this.venue_name = venue_name;
        this.venue_address = venue_address;
        this.venue_city = venue_city;
        this.venue_state = venue_state;
        this.venue_zip = venue_zip;
        this.contact_id = contact_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }
}
