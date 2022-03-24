package com.btewebquest.model;

import java.util.ArrayList;
import java.util.List;

public class TrackModel {

    private int id;
    private String trackName;
    private int trackNumber;
    private List<TrackProgressModel> tracksProgress;

    public TrackModel() {}

    public TrackModel(int id, String trackName, int trackNumber) {
        this.id = id;
        this.trackName = trackName;
        this.trackNumber = trackNumber;
        this.tracksProgress = new ArrayList<TrackProgressModel>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public List<TrackProgressModel> getTracksProgress() {
        return tracksProgress;
    }

    public void setTracksProgress(List<TrackProgressModel> tracksProgress) {
        this.tracksProgress = tracksProgress;
    }
}
