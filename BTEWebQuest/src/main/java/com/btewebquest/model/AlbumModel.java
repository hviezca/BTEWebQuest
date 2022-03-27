package com.btewebquest.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model representing an Album
 *
 * @author sfradet
 * @version 1.0
 */
public class AlbumModel {

    private int id; // Album ID
    private String albumName; // Album Name
    private int albumYear; // Album Year
    private boolean isMixed; // Is Album mixed
    private boolean isMastered; // Is Album mastered
    private List<TrackModel> trackList; // List of Tracks for Album

    public AlbumModel() {}

    public AlbumModel(int id, String albumName, int albumYear, boolean isMixed, boolean isMastered) {
        this.id = id;
        this.albumName = albumName;
        this.albumYear = albumYear;
        this.isMixed = isMixed;
        this.isMastered = isMastered;
        this.trackList = new ArrayList<TrackModel>() {
        };
    }

    // Getter and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getAlbumYear() {
        return albumYear;
    }

    public void setAlbumYear(int albumYear) {
        this.albumYear = albumYear;
    }

    public boolean isMixed() {
        return isMixed;
    }

    public void setMixed(boolean mixed) {
        isMixed = mixed;
    }

    public boolean isMastered() {
        return isMastered;
    }

    public void setMastered(boolean mastered) {
        isMastered = mastered;
    }

    public List<TrackModel> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<TrackModel> trackList) {
        this.trackList = trackList;
    }
}
