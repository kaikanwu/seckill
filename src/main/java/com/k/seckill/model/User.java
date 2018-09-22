package com.k.seckill.model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="user")
public class User implements Serializable{

    private static final long serialVersionUID = 7521391360002308184L;

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Id
    @Column(name="username")
    @NotBlank(message="用户名不能为空")
    private String username;

    @NotBlank(message="密码不能为空")
    @Column(name="password", nullable = false)
    private String password;

    @Column(name="id", nullable = false)
    private long id;

    @Column(name="dbflag")
    private String dbflag;


    private String repassword;


    public String getDbflag() {
        return dbflag;
    }

    public void setDbflag(String dbflag) {
        this.dbflag = dbflag;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", dbflag='" + dbflag + '\'' +
                ", repassword='" + repassword + '\'' +
                '}';
    }
}
