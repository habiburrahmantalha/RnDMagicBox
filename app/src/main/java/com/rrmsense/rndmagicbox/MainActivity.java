package com.rrmsense.rndmagicbox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_holder)
    FrameLayout fragmentHolder;
    ArrayList<String> deviceNameArrayList;
    int CURRENT_FRAGMENT = Constants.FRAGMENT_DEVICE;
    @BindView(R.id.device)
    Button device;
    @BindView(R.id.room)
    Button room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getDeviceList();

    }

    public void openFragment(int fragmentID) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new Fragment();
        Bundle bundle = new Bundle();
        switch (fragmentID) {
            case Constants.FRAGMENT_DEVICE:
                CURRENT_FRAGMENT = Constants.FRAGMENT_DEVICE;
                fragment = new DeviceFragment();
                break;
            case Constants.FRAGMENT_ROOM:
                CURRENT_FRAGMENT = Constants.FRAGMENT_ROOM;
                fragment = new RoomFragment();
                break;
            case Constants.FRAGMENT_DEVICE_CONTROLLER:
                CURRENT_FRAGMENT = Constants.FRAGMENT_DEVICE_CONTROLLER;
                fragment = new DeviceControllerFragment();
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
        if (CURRENT_FRAGMENT == Constants.FRAGMENT_ROOM)
            openFragment(Constants.FRAGMENT_DEVICE);
        else
            super.onBackPressed();
    }

    private void getDeviceList() {
        deviceNameArrayList = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            deviceNameArrayList.add("Port_" + i);
            Device device = new Device("Port_" + i,"Port_" + i,"","","",false,false);

            String json = Storage.getDevice(this,deviceNameArrayList.get(i-1));
            if(json.equals("")){
                Gson gson = new Gson();
                json = gson.toJson(device);
                Storage.setDevice(this,deviceNameArrayList.get(i-1),json);
            }
        }

    }

    @OnClick({R.id.device, R.id.room})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.device:
                openFragment(Constants.FRAGMENT_DEVICE);
                break;
            case R.id.room:
                openFragment(Constants.FRAGMENT_ROOM);
                break;

        }
    }
}
