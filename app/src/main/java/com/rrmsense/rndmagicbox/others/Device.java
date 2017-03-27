package com.rrmsense.rndmagicbox.others;

/**
 * Created by Talha on 3/25/2017.
 */

public class Device {
    String address;
    String deviceName;
    String roomName;
    String deviceType;
    String roomType;
    Boolean status;
    Boolean using;

    public Device() {
    }

    public Device(String address, String deviceName, String roomName, String deviceType, String roomType, Boolean status, Boolean using) {
        this.address = address;
        this.deviceName = deviceName;
        this.roomName = roomName;
        this.deviceType = deviceType;
        this.roomType = roomType;
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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
