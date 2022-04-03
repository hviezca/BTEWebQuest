package com.btewebquest.controller;

import com.btewebquest.business.AlbumBusinessService;
import com.btewebquest.business.TrackBusinessService;
import com.btewebquest.model.AlbumModel;
import com.btewebquest.model.TrackModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * Controller for dealing with Coming Soon Albums
 *
 * @author sfradet
 * @version 1.0 *
 */
@Controller
@RequestMapping("/BTE")
public class AlbumController {

    @Autowired
    private AlbumBusinessService albumService;

    @Autowired
    private TrackBusinessService trackService;

    /**
     * Return page with Coming Soon Album information
     *
     * @param model ViewModel
     * @return coming soon HTML page
     */
    @GetMapping("/comingsoon")
    public String comingSoon(Model model)
    {
        // Retrieve List of albums
        List<AlbumModel> albums = albumService.getAlbums();

        // Get Album 1 from database
        AlbumModel album = albumService.getAlbumById(1);

        // Get Albums tracks from database
        album.setTrackList(albumService.getTracks(album.getId()));

        // Setup information for View Model
        model.addAttribute("title", "Progress Management");
        model.addAttribute("album", album);

        // Return HTML page
        return "coming-soon/coming-soon-manage";
    }

    /**
     * Add new Track to database
     *
     * @param id ID of Album linked to Track
     * @param model View Model
     * @return Coming Soon HTML page
     */
    @PutMapping("/comingsoon/addtrack/{id}")
    public String addNewTrack(@PathVariable("id") int id, Model model)
    {
        // Add new track set to Album ID and defaults for other parameters
        trackService.addTrack(new TrackModel(id));

        // Retrieve List of albums
        List<AlbumModel> albums = albumService.getAlbums();

        // Get Album 1 from database
        AlbumModel album = albumService.getAlbumById(1);

        // Get Albums tracks from database
        album.setTrackList(albumService.getTracks(album.getId()));

        // Setup information for View Model
        model.addAttribute("title", "Progress Management");
        model.addAttribute("album", album);

        return "coming-soon/coming-soon-menu-frag :: #comingSoonMenu";
    }

    /**
     * Delete Track by its ID
     *
     * @param id ID of Track to be deleted
     * @param model View Model
     * @return Coming Soon HTML page
     */
    @DeleteMapping("/comingsoon/deletetrack/{id}")
    public String deleteTrack(@PathVariable("id") int id, Model model)
    {
        // Get track to be deleted by its ID from database
        TrackModel track = trackService.getTrackById(id);

        // Delete track from database
        trackService.deleteTrack(track);

        // Retrieve List of albums
        List<AlbumModel> albums = albumService.getAlbums();

        // Get Album 1 from database
        AlbumModel album = albumService.getAlbumById(1);

        // Get Albums tracks from database
        album.setTrackList(albumService.getTracks(album.getId()));

        // Setup information for View Model
        model.addAttribute("title", "Progress Management");
        model.addAttribute("album", album);

        return "coming-soon/coming-soon-menu-frag :: #comingSoonMenu";
    }

    /**
     * Get TrackModel by its ID and return as ResponseEntity
     *
     * @param id ID of Track to be retrieved
     * @return Coming Soon HTML page
     */
    @GetMapping("/comingsoon/json/{id}")
    public ResponseEntity<?> getTrackJson(@PathVariable("id") int id)
    {
        try
        {
            // Get track from database
            TrackModel track = trackService.getTrackById(id);

            // If null, return 'Not found' else return Track
            if (track == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(track, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update Track
     *
     * @param track TrackModel to be updated
     * @param model View Model
     * @return Coming Soon HTML page
     */
    @RequestMapping("/comingsoon/trackupdate")
    public String updateTrack(@RequestBody TrackModel track, Model model)
    {
        // Update Track in database
        trackService.updateTrack(track);

        // Retrieve List of albums
        List<AlbumModel> albums = albumService.getAlbums();

        // Get Album 1 from database
        AlbumModel album = albumService.getAlbumById(1);

        // Get Albums tracks from database
        album.setTrackList(albumService.getTracks(album.getId()));

        // Setup information for View Model
        model.addAttribute("title", "Progress Management");
        model.addAttribute("album", album);

        return "coming-soon/coming-soon-menu-frag :: #comingSoonMenu";
    }

    /**
     * Retrieve TrackList for album
     *
     * @param album AlbumModel to be updated
     * @param model View Model
     * @return Coming Soon HTML page
     */
    @RequestMapping("/comingsoon/albumupdate")
    public String updateAlbum(@RequestBody AlbumModel album, Model model)
    {
        // Get Tracks for Album
        album.setTrackList(albumService.getTracks(album.getId()));

        album.setAlbumImage(albumService.getAlbumById(album.getId()).getAlbumImage());

        // Update Album in database
        albumService.updateAlbum(album);

        // Setup information for View Model
        model.addAttribute("title", "Progress Management");
        model.addAttribute("album", album);

        return "coming-soon/coming-soon-menu-frag :: #comingSoonMenu";
    }

    /**
     * Upload an album image
     *
     * @param file File to be uploaded
     * @param model View model
     * @return  Coming Soon HTML fragment
     * @throws IOException
     */
    @PostMapping("/comingsoon/albumimage")
    public String upload(@RequestParam("file") MultipartFile file, Model model) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path uploadPath = Paths.get("user-uploads/");

        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Retrieve List of albums
        List<AlbumModel> albums = albumService.getAlbums();

        // Get Album 1 from database
        AlbumModel album = albumService.getAlbumById(1);

        // Update album in database
        album.setAlbumImage(fileName);
        albumService.updateAlbum(album);

        // Retrieve List of albums
        albums = albumService.getAlbums();

        // Get Album 1 from database
        album = albumService.getAlbumById(1);

        // Get Albums tracks from database
        album.setTrackList(albumService.getTracks(album.getId()));

        // Setup information for View Model
        model.addAttribute("title", "Progress Management");
        model.addAttribute("album", album);

        // Return HTML page
        return "coming-soon/coming-soon-manage";
    }
}
