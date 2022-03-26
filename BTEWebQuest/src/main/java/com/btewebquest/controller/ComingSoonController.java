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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/BTE")
public class ComingSoonController {

    @Autowired
    private AlbumBusinessService albumService;

    @Autowired
    private TrackBusinessService trackService;

    @GetMapping("/comingsoon")
    public String comingSoon(Model model)
    {
        List<AlbumModel> albums = albumService.getAlbums();

        AlbumModel album = albumService.getAlbumById(1);

        album.setTrackList(albumService.getTracks(album.getId()));

        model.addAttribute("title", "Progress Management");
        model.addAttribute("album", album);

        return "coming-soon/coming-soon-manage";
    }

    @PutMapping("/comingsoon/addtrack/{id}")
    public String addNewTrack(@PathVariable("id") int id, Model model)
    {
        trackService.addTrack(new TrackModel(id));

        List<AlbumModel> albums = albumService.getAlbums();

        AlbumModel album = albumService.getAlbumById(1);

        album.setTrackList(albumService.getTracks(album.getId()));

        model.addAttribute("title", "Progress Management");
        model.addAttribute("album", album);

        return "coming-soon/coming-soon-menu-frag :: #comingSoonMenu";
    }

    @DeleteMapping("/comingsoon/deletetrack/{id}")
    public String deleteTrack(@PathVariable("id") int id, Model model)
    {

        TrackModel track = trackService.getTrackById(id);
        trackService.deleteTrack(track);

        List<AlbumModel> albums = albumService.getAlbums();

        AlbumModel album = albumService.getAlbumById(1);

        album.setTrackList(albumService.getTracks(album.getId()));

        model.addAttribute("title", "Progress Management");
        model.addAttribute("album", album);

        return "coming-soon/coming-soon-menu-frag :: #comingSoonMenu";
    }

    @GetMapping("/comingsoon/json/{id}")
    public ResponseEntity<?> getTrackJson(@PathVariable("id") int id)
    {
        try
        {
            TrackModel track = trackService.getTrackById(id);
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

    @RequestMapping("/comingsoon/trackupdate")
    public String updateTrack(@RequestBody TrackModel track, Model model)
    {
        trackService.updateTrack(track);

        List<AlbumModel> albums = albumService.getAlbums();

        AlbumModel album = albumService.getAlbumById(1);

        album.setTrackList(albumService.getTracks(album.getId()));

        model.addAttribute("title", "Progress Management");
        model.addAttribute("album", album);

        return "coming-soon/coming-soon-menu-frag :: #comingSoonMenu";
    }

    @RequestMapping("/comingsoon/albumupdate")
    public String updateAlbum(@RequestBody AlbumModel album, Model model)
    {
        System.out.println(album.getAlbumName() + " " + album.getAlbumYear());
        album.setTrackList(albumService.getTracks(album.getId()));

        //albumService.updateAlbum(album);

        List<AlbumModel> albums = albumService.getAlbums();

        model.addAttribute("title", "Progress Management");
        model.addAttribute("album", album);

        return "coming-soon/coming-soon-menu-frag :: #comingSoonMenu";
    }

}
