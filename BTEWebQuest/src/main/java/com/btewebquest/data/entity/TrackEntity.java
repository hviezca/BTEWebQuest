package com.btewebquest.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity representing a TrackModel object
 *
 * @author sfradet
 * @version 1.0
 */
@Table("TRACKS")
public class TrackEntity {

    @Id
    @Column("TRACK_ID")
    private int id;

    @Column("TRACK_NAME")
    private String trackName;

    @Column("TRACK_NUMBER")
    private int trackNumber;

    @Column("ALBUM_ID")
    private int albumId;

    @Column("VOCALS")
    private boolean vocals;

    @Column("GUITAR")
    private boolean guitar;

    @Column("DRUMS")
    private boolean drums;

    @Column("BASS")
    private boolean bass;

    public TrackEntity() {
    }

    public TrackEntity(int id, String trackName, int trackNumber, int albumId, boolean vocals, boolean guitar, boolean drums, boolean bass) {
        this.id = id;
        this.trackName = trackName;
        this.trackNumber = trackNumber;
        this.albumId = albumId;
        this.vocals = vocals;
        this.guitar = guitar;
        this.drums = drums;
        this.bass = bass;
    }

    // Getters and Setters
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
