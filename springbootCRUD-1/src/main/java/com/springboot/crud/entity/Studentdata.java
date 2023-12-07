package com.springboot.crud.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;

import jakarta.persistence.Id;

@Entity
@Table(name = "studentdata")
public class Studentdata {

    @Id
   
    private int id;

    private String firstname;
    private String lastname;
    private String email;
    private String mobile;

    // Constructors, getters, setters

    // Constructors
    public Studentdata() {
    }

    public Studentdata(String firstname, String lastname, String email, String mobile) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobile = mobile;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
