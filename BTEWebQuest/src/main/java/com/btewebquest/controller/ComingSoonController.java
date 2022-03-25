package com.btewebquest.controller;

import com.btewebquest.business.AlbumBusinessService;
import com.btewebquest.business.TrackBusinessService;
import com.btewebquest.model.AlbumModel;
import com.btewebquest.model.TrackModel;
import com.btewebquest.model.TrackProgressModel;
import com.btewebquest.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("title", "User Management");

        List<AlbumModel> albums = albumService.getAlbums();

        AlbumModel album = albumService.getAlbumById(1);

        album.setTrackList(albumService.getTracks(album.getId()));

        for (TrackModel track : album.getTrackList())
        {
            track.setTracksProgress(albumService.getTrackProgress(track.getId()));
            for (TrackProgressModel trackProgress : track.getTracksProgress())
            {
                track.getTracksHash().put(trackProgress.getInstrumentName(), trackProgress.isRecorded());
            }
        }

        model.addAttribute("title", "Progress Management");
        model.addAttribute("album", album);

        return "coming-soon/coming-soon-manage";
    }

    @GetMapping("/comingsoon/addTrack/{id}")
    public String addNewTrack(@PathVariable("id") int id)
    {


        return "admin/userFragment :: #userTable";
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

}
