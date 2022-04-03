package com.btewebquest.controller;

import com.btewebquest.business.UserBusinessService;
import com.btewebquest.model.UserModel;
import com.btewebquest.model.ValidatedResponse;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller for handling actions related to Users
 *
 * @author sfradet
 * @version 1/0
 */
@Controller
@RequestMapping("/BTE")
public class UserController {
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
    public String users(Model model) {
        // If user securely logged in, add to session data.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            int userID = service.findUserByUsername(currentUserName).getId();
            if (null == session.getAttribute("user")) {
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
    public ResponseEntity<?> getUserJson(@PathVariable("id") int id) {

        try {
            // Get User from database
            UserModel user = service.getUserById(id);

            // If User is null, send fail, else send UserModel
            if (user == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete UserModel from database
     *
     * @param id    ID of desired User to delete
     * @param model View Model
     * @return User Management Page
     */
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id, Model model) {

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
     * @param user   UserModel to add to database
     * @param result BindingResult for validation
     * @param model  View Model
     * @return User Management Page
     */
    @PostMapping("/users")
    public String addUser(@ModelAttribute @Valid UserModel user, BindingResult result, Model model) {

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
     * @param user   UserModel to be updated
     * @param result BindingResult for validation
     * @param model  View Model
     * @return User Management page
     */
    @PutMapping("/users")
    public String updateUser(@ModelAttribute @Valid UserModel user, BindingResult result, Model model) {

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
     * @param user  UserModel to be deleted
     * @param model View Model
     * @return User Management Page
     */
    @RequestMapping("/users/verify")
    public String verifyDeleteUser(@RequestBody UserModel user, Model model) {

        // Get Admin user from database
        UserModel admin = service.getUserById((Integer) session.getAttribute("userID"));

        // Get User to be deleted from database
        UserModel deleteUser = service.getUserById(user.getId());

        // Verify User entered Admin Password
        if (BCrypt.checkpw(user.getPassword(), admin.getPassword())) {
            service.deleteUser(deleteUser);
        } else {
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
     * @param user  UserModel to have password updated
     * @param model View Model
     * @return User Management Page
     */
    @RequestMapping("/users/update")
    public String updateUserPassword(@RequestBody UserModel user, Model model) {

        // Get User from database
        UserModel oldUser = service.getUserById(user.getId());

        String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
        // Update User
        oldUser.setPassword(encoded);
        service.updateUser(oldUser);

        // Get List of UserModel
        List<UserModel> users = service.getUsers();

        // Setup information for View Model
        model.addAttribute("title", "User Management");
        model.addAttribute("userList", users);
        model.addAttribute("userModel", new UserModel());

        return "admin/user-management/userFragment :: #userTable";
    }

    /**
     * Validate a UserModel and return results
     *
     * @param user UserModel for validation
     * @param result Holds results from validation
     * @return ValidatedResponse with validation status and any possilbe errors
     */
    @PostMapping("/users/validation")
    @ResponseBody
    public ValidatedResponse validateUser(@ModelAttribute @Valid UserModel user, BindingResult result) {

        ValidatedResponse response = new ValidatedResponse();

        boolean editingUser = false; // is user being edited
        boolean invalidUsername = false; // is requested username invalid

        // Verify if user is being updated
        if(user.getPassword().equals("updatingUser")){
            editingUser = true;
        }

        // Verify if requested username is already in use.
        try{
            // Check database for username
            UserModel verifyUser = service.findUserByUsername(user.getUserName());

            // Check database results and verify if requested username is in use by current user
            if((verifyUser.getUserName() != null) && !verifyUser.getUserName().equals(session.getAttribute("user")))
            {
                invalidUsername = true;
            }
            // If requested username matches current user and they are not editing, flag error
            else if (verifyUser.getUserName().equals(session.getAttribute("user")) && !editingUser)
            {
                invalidUsername = true;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        // Collect any errors
        if (result.hasErrors() || invalidUsername) {

            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );

            if(invalidUsername)
            {
                errors.put("unique", "Username already in use");
            }

            response.setValidated(false);
            response.setErrorMessages(errors);
        } else {
            response.setValidated(true);
        }

        return response;
    }

    /**
     * Validate password data for update
     *
     * @param user UserModel with new data
     * @param result Validated results
     * @return ValidatedResponse with validated status and errors if applicable
     */
    @PostMapping("/users/passwordvalidation")
    @ResponseBody
    public ValidatedResponse validatePassword(@ModelAttribute @Valid UserModel user, BindingResult result) {

        ValidatedResponse response = new ValidatedResponse();
        boolean passwordValidated = false;

        // Get User from database
        UserModel oldUser = service.getUserById(user.getId());

        // Verify User password
        if (BCrypt.checkpw(user.getUserName(), oldUser.getPassword())) {
            String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
            passwordValidated = true;
        }

        // Collect any errors
        if (result.hasErrors() || !passwordValidated) {

            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );

            if(!passwordValidated)
            {
                errors.put("oldPassword", "Password does not match");
            }

            response.setValidated(false);
            response.setErrorMessages(errors);
        } else {
            response.setValidated(true);
        }

        return response;
    }

}
