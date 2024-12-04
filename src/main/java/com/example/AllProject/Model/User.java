package com.example.AllProject.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")  // Specify the column name
    private Integer id;  // Renamed to "id" to follow JavaBean convention

    private String username;
    private String address;
    private String email;
    private String password;
    private String role;

    // Default constructor
    public User() {
    }

    // Constructor with parameters
    public User(Integer id, String username, String address, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and setters

    public Integer getId() {
        return id;  // Getter for "id"
    }

    public void setId(Integer id) {
        this.id = id;  // Setter for "id"
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // toString method to display User object
    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}