package com.jgo.video.data.bean;

import java.util.Date;

/**
 * Created by ke-oh on 2020-04-26.
 */
public class ScheduleInfo {

    private String homeTeamId;
    private String guestTeamId;
    private Date date;

    public ScheduleInfo(String homeTeamId, String guestTeamId, Date date) {
        this.homeTeamId = homeTeamId;
        this.guestTeamId = guestTeamId;
        this.date = date;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getGuestTeamId() {
        return guestTeamId;
    }

    public void setGuestTeamId(String guestTeamId) {
        this.guestTeamId = guestTeamId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
