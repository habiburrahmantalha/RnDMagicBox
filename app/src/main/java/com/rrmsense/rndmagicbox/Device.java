package com.rrmsense.rndmagicbox;

/**
 * Created by Talha on 3/25/2017.
 */

public class Device {
    String address;
    String deviceName;
    String roomName;
    String type;
    String room;
    Boolean status;
    Boolean using;

    public Device() {
    }

    public Device(String address, String deviceName, String roomName, String type, String room, Boolean status, Boolean using) {
        this.address = address;
        this.deviceName = deviceName;
        this.roomName = roomName;
        this.type = type;
        this.room = room;
        this.status = status;
        this.using = using;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getUsing() {
        return using;
    }

    public void setUsing(Boolean using) {
        this.using = using;
    }
}
