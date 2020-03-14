package com.example.msvs3;

public class VoteEvent {
    String Date;
    String starttime;
    String endtime;
    String eventname;

    public VoteEvent() {
    }

    public VoteEvent(String date, String starttime, String endtime, String eventname) {
        Date = date;
        this.starttime = starttime;
        this.endtime = endtime;
        this.eventname = eventname;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }
}
