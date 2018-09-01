package com.k.seckill.model;



import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name = "user")
public class User implements Serializable{
    private static final long serialVersionUID = 7521391360002308184L;


    /**
     * @NotBlank -> use the JSR 303 做服务端验证，具体方法是添加注解
     * 服务端验证比客户端的验证更加可靠，
     * TODO notBlank 和 notnull 的区别
     */
    @javax.persistence.Id
    @Column(name="username")
    @NotBlank(message = "username不能为空")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "password不能为空")
    private String password;
//    @Size(min = 4, max = 6, message = "password的长度需要4到6个字符")


    @Column(name = "id")
    private Integer id;


    /**
     * used for md5
     */
    @Column(name = "dbflag")
    private String dbflag;



    public User() {


    }

    private String repassword;



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



    /**
     * Setter and getter
     * @return
     */



    public String getDbflag() {
        return dbflag;
    }

    public void setDbflag(String dbflag) {
        this.dbflag = dbflag;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }



    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
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
