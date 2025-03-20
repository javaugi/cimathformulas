package com.spring5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_USERS")
public class User {
    
    public User() {
        
    }

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    //@Size(max = 20, min = 3, message = "{user.name.invalid}")
    //@NotEmpty(message = "Please Enter your name")
    private String name;

    @Column(name = "USER_EMAIL", unique = true)
    //@Email(message = "{user.email.invalid}")
    //@NotEmpty(message = "Please Enter your email")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
