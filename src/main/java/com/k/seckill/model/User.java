package com.k.seckill.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User {


    @Id
    @Column(name="username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "id")
    private Integer id;





    public User() {


    }

    public User(String userName, String password, Integer id ) {
        this.userName = userName;
        this.password = password;
        this.id = id;

    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
