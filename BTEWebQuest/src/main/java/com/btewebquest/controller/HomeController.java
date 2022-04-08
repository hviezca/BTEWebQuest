/**
 * HOME CONTROLLER
 * A Controller class for the Application Main page
 * Author @ Hiram Viezca
 */

package com.btewebquest.controller;

import com.btewebquest.business.AlbumBusinessService;
import com.btewebquest.business.EventBusinessService;
import com.btewebquest.business.VenueBusinessService;
import com.btewebquest.model.AlbumModel;
import com.btewebquest.model.EventModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AlbumBusinessService albumService;

    @Autowired
    private EventBusinessService eventService;

    @Autowired
    private VenueBusinessService venueService;

    /**
     * Displays the Website Main page
     * @return index.html
     */
    @GetMapping("/")
    public String home(Model model)
    {
        // Retrieve List of albums
        List<AlbumModel> albums = albumService.getAlbums();

        // Get Album 1 from database
        AlbumModel album = albumService.getAlbumById(1);

        // Get Albums tracks from database
        album.setTrackList(albumService.getTracks(album.getId()));

        // Get List of all Events from database
        List<EventModel> events = eventService.getEvents();

        for(EventModel event : events)
        {
            event.setVenue(venueService.findVenueById(event.getVenue().getVenue_id()));
        }

        // Setup information for View Model
        model.addAttribute("title", "Admin Menu");
        model.addAttribute("album", album);
        model.addAttribute("eventList", events);

        return "home/index";
    }
}
