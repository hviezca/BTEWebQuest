package com.btewebquest.model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MessageModel {

    @Id
    private int message_id;

    private String subject;

    @NotEmpty
    @Size(min = 4, max = 2000, message = "Message must be between 4 and 2000 characters")
    private String message;

    private boolean has_reply;

    public MessageModel() {}

    public MessageModel(int message_id, String subject, String message, boolean has_reply) {
        this.message_id = message_id;
        this.subject = subject;
        this.message = message;
        this.has_reply = has_reply;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
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

    @Override
    public String toString() {
        return "MessageModel{" +
                "message_id=" + message_id +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", has_reply=" + has_reply +
                '}';
    }
}
