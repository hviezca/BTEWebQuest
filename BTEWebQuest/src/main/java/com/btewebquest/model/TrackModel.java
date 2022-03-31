package com.btewebquest.model;

import java.text.DecimalFormat;

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

    public double getTrackCompletionPercent()
    {
        double total = 0;

        if (this.vocals)
        {
            total += .25;
        }
        if (this.drums)
        {
            total += .25;
        }
        if (this.bass)
        {
            total += .25;
        }
        if (this.guitar)
        {
            total += .25;
        }

        return total;
    }

    public String getTrackCompletionPercentFormatted()
    {
        DecimalFormat df = new DecimalFormat("0");
        double percentFormatted = this.getTrackCompletionPercent() * 100;
        return df.format(percentFormatted);
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
