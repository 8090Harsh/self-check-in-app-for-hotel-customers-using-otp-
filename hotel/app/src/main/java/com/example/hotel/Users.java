package com.example.hotel;

public class Users {
    String room,guest;

    public Users() {

    }

    public Users(String room, String guest) {
        this.room = room;
        this.guest = guest;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }
}
