package com.btewebquest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrackModel {

    private int id;
    private String trackName;
    private int trackNumber;
    private int albumId;
    private List<TrackProgressModel> tracksProgress;
    private HashMap<String, Boolean> tracksHash;

    public TrackModel() {}

    public TrackModel(int id, String trackName, int trackNumber, int albumId) {
        this.id = id;
        this.trackName = trackName;
        this.trackNumber = trackNumber;
        this.albumId = albumId;
        this.tracksProgress = new ArrayList<TrackProgressModel>();
        this.tracksHash = new HashMap<String, Boolean>();
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

    public HashMap<String, Boolean> getTracksHash() {
        return tracksHash;
    }

    public void setTracksHash(HashMap<String, Boolean> tracksHash) {
        this.tracksHash = tracksHash;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
}
