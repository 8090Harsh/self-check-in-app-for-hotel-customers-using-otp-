package com.example.hotel;

public class Usr {
    String Time,Date;

    public Usr() {

    }

    public Usr(String time, String date) {
        Time = time;
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}

