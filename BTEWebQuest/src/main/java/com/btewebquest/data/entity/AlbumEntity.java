package com.btewebquest.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity representing and AlbumModel object
 *
 * @author sfradet
 * @version 1.0
 */
@Table("ALBUM")
public class AlbumEntity {

    @Id
    @Column("ALBUM_ID")
    private int id;

    @Column("ALBUM_NAME")
    private String albumName;

    @Column("ALBUM_YEAR")
    private int albumYear;

    @Column("IS_MIXED")
    private boolean isMixed;

    @Column("IS_MASTERED")
    private boolean isMastered;

    @Column("IMAGE_NAME")
    private String imageName;

    public AlbumEntity() {
    }

    public AlbumEntity(int id, String albumName, int albumYear, boolean isMixed, boolean isMastered, String imageName) {
        this.id = id;
        this.albumName = albumName;
        this.albumYear = albumYear;
        this.isMixed = isMixed;
        this.isMastered = isMastered;
        this.imageName = imageName;
    }

    // Getters / Setters
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
