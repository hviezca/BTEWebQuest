package com.btewebquest.model;

/**
 * Model representing an Album Track
 *
 * @author sfradet
 * @version 1.0
 */
public class TrackModel {

    private int id; // Track ID
    private String trackName; // Track Name
    private int trackNumber; // Track Number
    private int albumId; // Album ID
    private boolean vocals; // Is vocals recorded
    private boolean guitar; // is guitar recorded
    private boolean drums; // is drums recorded
    private boolean bass; // is bass recorded

    public TrackModel() {}

    public TrackModel(int albumId) {
        this.id= 0;
        this.trackName= "Track Title";
        this.trackNumber= 0;
        this.vocals = false;
        this.guitar = false;
        this.drums = false;
        this.bass = false;
        this.albumId = albumId;
    }

    public TrackModel(int id, String trackName, int trackNumber, int albumId, boolean vocals, boolean guitar, boolean drums, boolean bass) {
        this.id = id;
        this.trackName = trackName;
        this.trackNumber = trackNumber;
        this.albumId = albumId;
        this.vocals = vocals;
        this.guitar = guitar;
        this.drums = drums;
        this.bass = bass;
    }

    // Getter and setters

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

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public boolean isVocals() {
        return vocals;
    }

    public void setVocals(boolean vocals) {
        this.vocals = vocals;
    }

    public boolean isGuitar() {
        return guitar;
    }

    public void setGuitar(boolean guitar) {
        this.guitar = guitar;
    }

    public boolean isDrums() {
        return drums;
    }

    public void setDrums(boolean drums) {
        this.drums = drums;
    }

    public boolean isBass() {
        return bass;
    }

    public void setBass(boolean bass) {
        this.bass = bass;
    }
}
