package com.btewebquest.controller;

import com.btewebquest.business.AlbumBusinessService;
import com.btewebquest.model.AlbumModel;
import com.btewebquest.model.TrackModel;
import com.btewebquest.model.TrackProgressModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/BTE")
public class ComingSoonController {

    @Autowired
    private AlbumBusinessService service;

    @GetMapping("/comingsoon")
    public String comingSoon(Model model)
    {
        model.addAttribute("title", "User Management");

        List<AlbumModel> albums = service.getAlbums();

        for (AlbumModel album : albums)
        {

            System.out.println(album.getAlbumName());
            System.out.println(album.getAlbumYear());
            System.out.println(album.isMixed());
            System.out.println(album.isMastered());
            album.setTrackList(service.getTracks(album.getId()));
            for (TrackModel track : album.getTrackList())
            {
                track.setTracksProgress(service.getTrackProgress(track.getId()));
                System.out.println(track.getTrackName() + " " + track.getId());
                for (TrackProgressModel trackProgress : track.getTracksProgress())
                {
                    System.out.println(trackProgress.getInstrumentName());
                }
            }
        }


        return "coming-soon/coming-soon-manage";
    }

}
