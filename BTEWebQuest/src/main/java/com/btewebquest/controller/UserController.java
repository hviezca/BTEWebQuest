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

@Controller
@RequestMapping("/BTE")
public class UserController
{
    @Autowired
    private UserBusinessService service;

    @Autowired
    HttpSession session;

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

        List<UserModel> users = service.getUsers();

        model.addAttribute("title", "User Management");
        model.addAttribute("userList", users);

        return "admin/users";
    }

    @GetMapping("/users/json/{id}")
    public ResponseEntity<?> getUserJson(@PathVariable("id") int id)
    {
        try
        {
            UserModel user = service.getUserById(id);
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


    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id, Model model)
    {
        UserModel user = service.getUserById(id);

        service.deleteUser(user);

        List<UserModel> users = service.getUsers();

        model.addAttribute("title", "User Management");
        model.addAttribute("userList", users);

        return "admin/userFragment";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute @Valid UserModel user, BindingResult result, Model model)
    {
        // Encode Password
        String encoded = new BCryptPasswordEncoder().encode(user.getPassword());

        // Add encoded password to UserModel
        user.setPassword(encoded);

        service.addUser(user);

        List<UserModel> users = service.getUsers();

        model.addAttribute("title", "User Management");
        model.addAttribute("userList", users);
        model.addAttribute("userModel", new UserModel());

        return "admin/userFragment :: #userTable";
    }

    @PutMapping("/users")
    public String updateUser(@ModelAttribute @Valid UserModel user, BindingResult result, Model model)
    {
        UserModel existingUser = service.getUserById(user.getId());

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setUserName(user.getUserName());

        service.updateUser(existingUser);

        List<UserModel> users = service.getUsers();

        model.addAttribute("title", "User Management");
        model.addAttribute("userList", users);
        model.addAttribute("userModel", new UserModel());

        return "admin/userFragment :: #userTable";
    }

    @RequestMapping("/users/verify")
    public String verifyDeleteUser(@RequestBody UserModel user, Model model)
    {
        UserModel admin = service.getUserById((Integer) session.getAttribute("userID"));
        System.out.println(admin.getUserName());
        UserModel deleteUser = service.getUserById(user.getId());

        System.out.println(admin.getUserName());
        System.out.println(user.getId());

        if(BCrypt.checkpw(user.getPassword(), admin.getPassword()))
        {
            System.out.println(user.getId());
            service.deleteUser(deleteUser);
        }
        else
        {
            System.out.println("No Match");
        }

        List<UserModel> users = service.getUsers();

        model.addAttribute("title", "User Management");
        model.addAttribute("userList", users);
        model.addAttribute("userModel", new UserModel());

        return "admin/userFragment :: #userTable";
    }

}
