package com.btewebquest.controller;

import com.btewebquest.business.UserBusinessService;
import com.btewebquest.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Controller for handling actions related to Users
 *
 * @author sfradet
 * @version 1/0
 */
@Controller
@RequestMapping("/BTE")
public class UserController
{
    @Autowired
    private UserBusinessService service;

    @Autowired
    HttpSession session;

    /**
     * Return User page with list of all Users
     *
     * @param model View Model
     * @return User Management page
     */
    @GetMapping("/users")
    public String users(Model model)
    {
        // If user securely logged in, add to session data.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            int userID = service.findUserByUsername(currentUserName).getId();
            if(null == session.getAttribute("user"))
            {
                session.setAttribute("user", currentUserName);
                session.setAttribute("userID", userID);
            }
        }

        // Get List of all Users from database
        List<UserModel> users = service.getUsers();

        // Setup information for View Model
        model.addAttribute("title", "User Management");
        model.addAttribute("userList", users);

        return "admin/user-management/users";
    }

    /**
     * Return User data as ResponseEntity
     *
     * @param id ID of desired UserModel
     * @return User Management page
     */
    @GetMapping("/users/json/{id}")
    public ResponseEntity<?> getUserJson(@PathVariable("id") int id)
    {
        try
        {
            // Get User from database
            UserModel user = service.getUserById(id);

            // If User is null, send fail, else send UserModel
            if (user == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete UserModel from database
     *
     * @param id ID of desired User to delete
     * @param model View Model
     * @return User Management Page
     */
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id, Model model)
    {
        // Get User from database
        UserModel user = service.getUserById(id);

        // Delete User from database
        service.deleteUser(user);

        // Get List of all Users in database
        List<UserModel> users = service.getUsers();

        // Setup information for View Model
        model.addAttribute("title", "User Management");
        model.addAttribute("userList", users);

        return "admin/user-management/userFragment";
    }

    /**
     * Add User to database
     *
     * @param user UserModel to add to database
     * @param result BindingResult for validation
     * @param model View Model
     * @return User Management Page
     */
    @PostMapping("/users")
    public String addUser(@ModelAttribute @Valid UserModel user, BindingResult result, Model model)
    {
        // Encode Password
        String encoded = new BCryptPasswordEncoder().encode(user.getPassword());

        // Add encoded password to UserModel
        user.setPassword(encoded);

        // Add User to database
        service.addUser(user);

        // Get User by ID
        user = service.findUserByUsername(user.getUserName());

        // Add 'Editor' role to User
        service.addUserRoles(4, user.getId());

        // Get List of all Users from database
        List<UserModel> users = service.getUsers();

        // Setup information for View Model
        model.addAttribute("title", "User Management");
        model.addAttribute("userList", users);
        model.addAttribute("userModel", new UserModel());

        return "admin/user-management/userFragment :: #userTable";
    }

    /**
     * Update User in database
     *
     * @param user UserModel to be updated
     * @param result BindingResult for validation
     * @param model View Model
     * @return User Management page
     */
    @PutMapping("/users")
    public String updateUser(@ModelAttribute @Valid UserModel user, BindingResult result, Model model)
    {
        // Get User from database
        UserModel existingUser = service.getUserById(user.getId());

        // Update User information
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setUserName(user.getUserName());

        // Update User in database
        service.updateUser(existingUser);

        // Get List of User from database
        List<UserModel> users = service.getUsers();

        // Setup information for View Model
        model.addAttribute("title", "User Management");
        model.addAttribute("userList", users);
        model.addAttribute("userModel", new UserModel());

        return "admin/user-management/userFragment :: #userTable";
    }

    /**
     * Delete User from database
     *
     * @param user UserModel to be deleted
     * @param model View Model
     * @return User Management Page
     */
    @RequestMapping("/users/verify")
    public String verifyDeleteUser(@RequestBody UserModel user, Model model)
    {
        // Get Admin user from database
        UserModel admin = service.getUserById((Integer) session.getAttribute("userID"));

        // Get User to be deleted from database
        UserModel deleteUser = service.getUserById(user.getId());

        // Verify User entered Admin Password
        if(BCrypt.checkpw(user.getPassword(), admin.getPassword()))
        {
            service.deleteUser(deleteUser);
        }
        else
        {
            System.out.println("No Match");
        }

        // Get List of User from database
        List<UserModel> users = service.getUsers();

        // Setup information for View Model
        model.addAttribute("title", "User Management");
        model.addAttribute("userList", users);
        model.addAttribute("userModel", new UserModel());

        return "admin/user-management/userFragment :: #userTable";
    }

    /**
     * Update User Password
     *
     * @param user UserModel to have password updated
     * @param model View Model
     * @return User Management Page
     */
    @RequestMapping("/users/update")
    public String updateUserPassword(@RequestBody UserModel user, Model model)
    {
        // Get User from database
        UserModel oldUser = service.getUserById(user.getId());

        // Verify User password
        if(BCrypt.checkpw(user.getUserName(), oldUser.getPassword()))
        {
           // Encode Password
           String encoded = new BCryptPasswordEncoder().encode(user.getPassword());

           // Update User
           oldUser.setPassword(encoded);
           service.updateUser(oldUser);
        }
        else
        {
            System.out.println("No Match");
        }

        // Get List of UserModel
        List<UserModel> users = service.getUsers();

        // Setup information for View Model
        model.addAttribute("title", "User Management");
        model.addAttribute("userList", users);
        model.addAttribute("userModel", new UserModel());

        return "admin/user-management/userFragment :: #userTable";
    }
}
