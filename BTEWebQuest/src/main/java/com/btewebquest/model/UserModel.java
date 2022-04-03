package com.btewebquest.model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Model representing User
 *
 * @author sfradet
 * @version 1.0
 */
public class UserModel {

    @Id
    private int id; // User ID

    @NotEmpty(message="Please enter a first name")
    private String firstName; // First Name

    @NotEmpty(message="Please enter a last name")
    private String lastName; // Last Name

    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    private String userName; // Username

    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    private String password; // Password

    public UserModel() {}

    public UserModel(int id, String firstName, String lastName, String userName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
