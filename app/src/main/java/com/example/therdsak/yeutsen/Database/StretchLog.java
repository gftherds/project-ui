package com.example.therdsak.yeutsen.Database;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Noppharat on 10/10/2016.
 */

public class StretchLog {
    private UUID id;
    private String userid;
    private Date date;
    private Integer stretchid;

    public StretchLog(){
        this.id = UUID.randomUUID();
    }

    public StretchLog(UUID id){
        this.id = id;
        date = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStretchid() {
        return stretchid;
    }

    public void setStretchid(Integer stretchid) {
        this.stretchid = stretchid;
    }
}