package com.btewebquest.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

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

    public TrackEntity() {
    }

    public TrackEntity(int id, String trackName, int trackNumber, int albumId) {
        this.id = id;
        this.trackName = trackName;
        this.trackNumber = trackNumber;
        this.albumId = albumId;
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

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
}
