package com.k.seckill.model;



import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "user")
public class User implements Serializable{
    private static final long serialVersionUID = 7521391360002308184L;


    @javax.persistence.Id
    @Column(name="username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "id")
    private Integer id;

    public User() {


    }



    private String repassword;

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }



    public User(String userName, String password, Integer id ) {
        this.username = userName;
        this.password = password;
        this.id = id;

    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
