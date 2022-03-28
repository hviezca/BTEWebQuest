/**
 * MESSAGE ENTITY
 * An Entity class for Message objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("MESSAGE")
public class MessageEntity {

    @Id
    @Column("MESSAGE_ID")
    private int id;

    @Column("SUBJECT")
    private String subject;

    @Column("MESSAGE")
    private String message;

    @Column("HAS_REPLY")
    private boolean has_reply;

    public MessageEntity(){}

    public MessageEntity(int id, String subject, String message, boolean has_reply) {
        this.id = id;
        this.subject = subject;
        this.message = message;
        this.has_reply = has_reply;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHas_reply() {
        return has_reply;
    }

    public void setHas_reply(boolean has_reply) {
        this.has_reply = has_reply;
    }
}
