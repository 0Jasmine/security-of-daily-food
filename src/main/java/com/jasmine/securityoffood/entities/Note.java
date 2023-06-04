package com.jasmine.securityoffood.entities;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.TableField;

public class Note {
    private String notes_user;
    private Timestamp notes_date;
    private String user_email;
    private String notes_type;
    private String message;
    
    public String getNotes_user() {
        return notes_user;
    }
    public void setNotes_user(String notes_user) {
        this.notes_user = notes_user;
    }
    public Timestamp getNotes_date() {
        return notes_date;
    }
    public void setNotes_date(Timestamp notes_date) {
        this.notes_date = notes_date;
    }
    public String getUser_email() {
        return user_email;
    }
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
    public String getNotes_type() {
        return notes_type;
    }
    public void setNotes_type(String notes_type) {
        this.notes_type = notes_type;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "Note [notes_user=" + notes_user + ", notes_date=" + notes_date + ", user_email=" + user_email
                + ", notes_type=" + notes_type + ", message=" + message + "]";
    }
}
