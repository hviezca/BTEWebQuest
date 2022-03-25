package com.btewebquest.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("PROGRESS")
public class TrackProgressEntity {

    @Id
    @Column("PROGRESS_ID")
    private int id;

    @Column("INSTRUMENT_NAME")
    private String instrumentName;

    @Column("IS_RECORDED")
    private boolean isRecorded;

    @Column("TRACK_ID")
    private int trackId;

    public TrackProgressEntity() {
    }

    public TrackProgressEntity(int id, String instrumentName, boolean isRecorded, int trackId) {
        this.id = id;
        this.instrumentName = instrumentName;
        this.isRecorded = isRecorded;
        this.trackId = trackId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public boolean isRecorded() {
        return isRecorded;
    }

    public void setRecorded(boolean recorded) {
        isRecorded = recorded;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }
}
