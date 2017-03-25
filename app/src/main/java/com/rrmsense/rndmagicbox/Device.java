package com.rrmsense.rndmagicbox;

/**
 * Created by Talha on 3/25/2017.
 */

public class Device {
    String address;
    String type;
    String room;
    String status;

    public Device() {
    }

    public Device(String address, String type, String room, String status) {
        this.address = address;
        this.type = type;
        this.room = room;
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
