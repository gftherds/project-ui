package com.example.therdsak.yeutsen.Database;

import java.util.Date;

/**
 * Created by Noppharat on 10/10/2016.
 */

public class StretchLog {
    private Integer id;
    private String userid;
    private Date date;
    private Integer stretchid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
