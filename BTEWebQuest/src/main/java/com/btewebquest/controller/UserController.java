package com.btewebquest.controller;

import com.btewebquest.business.UserBusinessService;
import com.btewebquest.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/BTE/user")
public class UserController
{
    @Autowired
    private UserBusinessService service;

    @GetMapping("users")
    public String users()
    {
        List<UserModel> users = service.getUsers();

        System.out.println("Test Print");

        for(UserModel user : users)
        {
            System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getUserName() + " " + user.getPassword());
        }

        return "admin/test";
    }

    @GetMapping("delete")
    public String deleteUser()
    {
        return "admin/delete";
    }

}
