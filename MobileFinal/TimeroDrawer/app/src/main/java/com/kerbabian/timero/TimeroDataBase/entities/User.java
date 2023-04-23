package com.kerbabian.timero.TimeroDataBase.entities;

import java.io.Serializable;

public class User implements Serializable {

    private int user_id;
    private String user_name;
    private String user_password;

    public int getId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public String getName() {
        return user_name;
    }

    public void setName(String name) {
        this.user_name = name;
    }

    public String getPassword() {
        return user_password;
    }

    public void setPassword(String password) {
        this.user_password = password;
    }
}
