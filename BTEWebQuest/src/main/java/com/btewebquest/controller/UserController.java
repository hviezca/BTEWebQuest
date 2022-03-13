package com.btewebquest.controller;

import com.btewebquest.business.UserBusinessService;
import com.btewebquest.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/BTE/user")
public class UserController
{
    @Autowired
    private UserBusinessService service;

    @GetMapping("users")
    public String users(Model model)
    {
        List<UserModel> users = service.getUsers();

        model.addAttribute("title", "User Management");
        model.addAttribute("userList", users);

        return "admin/users";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable int id, Model model)
    {
        UserModel user = service.getUserById(id);

        service.deleteUser(user);

        List<UserModel> users = service.getUsers();

        model.addAttribute("title", "User Management");
        model.addAttribute("userList", users);

        return "admin/userFragment";
    }

}
