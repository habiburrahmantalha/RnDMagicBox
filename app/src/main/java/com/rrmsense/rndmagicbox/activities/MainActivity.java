package com.rrmsense.rndmagicbox.activities;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.rrmsense.rndmagicbox.R;
import com.rrmsense.rndmagicbox.fragments.DeviceControllerFragment;
import com.rrmsense.rndmagicbox.fragments.DeviceFragment;
import com.rrmsense.rndmagicbox.fragments.ProfileFragment;
import com.rrmsense.rndmagicbox.fragments.RoomFragment;
import com.rrmsense.rndmagicbox.others.Constants;
import com.rrmsense.rndmagicbox.others.Device;
import com.rrmsense.rndmagicbox.others.Storage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> deviceNameArrayList;
    public String roomName;
    @BindView(R.id.fragment_holder)
    FrameLayout fragmentHolder;
    int CURRENT_FRAGMENT = Constants.FRAGMENT_DEVICE;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    openFragment(Constants.FRAGMENT_ROOM);
                    return true;
               /* case R.id.navigation_dashboard:
                    openFragment(Constants.FRAGMENT_DEVICE);
                    return true;
                case R.id.navigation_notifications:

                    return true;*/
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getDeviceList();
        openFragment(Constants.FRAGMENT_ROOM);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelected(true);

    }

    private void getDeviceList() {
        deviceNameArrayList = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            deviceNameArrayList.add("Port_" + i);
            Device device = new Device("Port_" + i, "", "", "", "", false, false);

            String json = Storage.getDevice(this, deviceNameArrayList.get(i - 1));
            if (json.equals("")) {
                Gson gson = new Gson();
                json = gson.toJson(device);
                Storage.setDevice(this, deviceNameArrayList.get(i - 1), json);
            }
        }

    }

    public void openFragment(int fragmentID) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new Fragment();
        Bundle bundle = new Bundle();
        switch (fragmentID) {
            case Constants.FRAGMENT_DEVICE:
                if (CURRENT_FRAGMENT != Constants.FRAGMENT_DEVICE) {
                    CURRENT_FRAGMENT = Constants.FRAGMENT_DEVICE;
                    fragment = new DeviceFragment();
                } else return;
                break;
            case Constants.FRAGMENT_ROOM:
                if (CURRENT_FRAGMENT != Constants.FRAGMENT_ROOM) {
                    CURRENT_FRAGMENT = Constants.FRAGMENT_ROOM;
                    fragment = new RoomFragment();
                } else return;
                break;
            case Constants.FRAGMENT_DEVICE_CONTROLLER:
                if (CURRENT_FRAGMENT != Constants.FRAGMENT_DEVICE_CONTROLLER) {
                    CURRENT_FRAGMENT = Constants.FRAGMENT_DEVICE_CONTROLLER;
                    fragment = new DeviceControllerFragment();
                } else return;
                break;
            case Constants.FRAGMENT_PROFILE:
                if (CURRENT_FRAGMENT != Constants.FRAGMENT_PROFILE) {
                    CURRENT_FRAGMENT = Constants.FRAGMENT_PROFILE;
                    fragment = new ProfileFragment();
                } else return;
                break;

        }
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                //.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.fragment_holder, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {

        switch (CURRENT_FRAGMENT) {
            case Constants.FRAGMENT_ROOM:
                super.onBackPressed();
                break;
            case Constants.FRAGMENT_DEVICE:
                openFragment(Constants.FRAGMENT_ROOM);
                break;
            case Constants.FRAGMENT_DEVICE_CONTROLLER:
                openFragment(Constants.FRAGMENT_ROOM);
                break;
            default:
                super.onBackPressed();
                break;
        }
    }

    public void setRoomName(String s) {
        roomName = s;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        for(int i = 0; i < menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                openFragment(Constants.FRAGMENT_DEVICE);
                break;
            case R.id.action_profile:
                openFragment(Constants.FRAGMENT_PROFILE);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
