/**
 * HOME CONTROLLER
 * A Controller class for the Application Main page
 * Author @ Hiram Viezca
 */

package com.btewebquest.controller;

import com.btewebquest.business.AlbumBusinessService;
import com.btewebquest.model.AlbumModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AlbumBusinessService albumService;

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

        // Setup information for View Model
        model.addAttribute("title", "Progress Management");
        model.addAttribute("album", album);

        return "home/index";
    }

    /**
     * Displays the Admin Home page
     * @param model A Model object for transferring data to the View
     * @return admin-home.html
     */
    @GetMapping("/BTE")
    public String adminHome(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        model.addAttribute("username", currentUserName);
        return "admin/admin-home";
    }
}
