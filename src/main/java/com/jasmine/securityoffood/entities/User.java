package com.jasmine.securityoffood.entities;

import com.baomidou.mybatisplus.annotation.TableField;

public class User {
    private String name;
    private String email;
    @TableField("gender")
    private String gender;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "User [name=" + name + ", email=" + email + ", gender=" + gender + "]";
    }
}
