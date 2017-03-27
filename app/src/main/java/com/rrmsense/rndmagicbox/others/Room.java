package com.rrmsense.rndmagicbox.others;

import java.util.ArrayList;

/**
 * Created by Talha on 3/25/2017.
 */

public class Room {

    String name;
    ArrayList<Device> deviceArrayList;

    public Room() {
    }

    public Room(String name, ArrayList<Device> deviceArrayList) {
        this.name = name;
        this.deviceArrayList = deviceArrayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Device> getDeviceArrayList() {
        return deviceArrayList;
    }

    public void setDeviceArrayList(ArrayList<Device> deviceArrayList) {
        this.deviceArrayList = deviceArrayList;
    }
}
