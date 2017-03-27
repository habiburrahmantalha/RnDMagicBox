package com.rrmsense.rndmagicbox.others;

/**
 * Created by Talha on 3/27/2017.
 */

public class SpinnerItem {
    int icon;
    String name;

    public SpinnerItem(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
