package com.btewebquest.model;

public class TrackProgressModel {

    private int id;
    private String instrumentName;
    private boolean isRecorded;

    public TrackProgressModel() {}

    public TrackProgressModel(int id, String instrumentName, boolean isRecorded) {
        this.id = id;
        this.instrumentName = instrumentName;
        this.isRecorded = isRecorded;
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
}
