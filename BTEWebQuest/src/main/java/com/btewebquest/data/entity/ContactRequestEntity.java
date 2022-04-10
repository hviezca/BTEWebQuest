package com.btewebquest.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("CONTACT_REQUEST")
public class ContactRequestEntity {

    @Id
    @Column("CONTACT_ID")
    private int contact_request_id;

    @Column("DATE")
    private Date date;

    @Column("PERSON_ID")
    private int person_id;

    @Column("MESSAGE_ID")
    private int message_id;

    public ContactRequestEntity() {}

    public ContactRequestEntity(int contact_request_id, Date date, int person_id, int message_id) {
        this.contact_request_id = contact_request_id;
        this.date = date;
        this.person_id = person_id;
        this.message_id = message_id;
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

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }
}
