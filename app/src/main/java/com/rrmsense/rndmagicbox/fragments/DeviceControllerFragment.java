package com.rrmsense.rndmagicbox.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rrmsense.rndmagicbox.R;
import com.rrmsense.rndmagicbox.activities.MainActivity;
import com.rrmsense.rndmagicbox.adapters.RecyclerViewAdapterDeviceController;
import com.rrmsense.rndmagicbox.others.Device;
import com.rrmsense.rndmagicbox.others.Storage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeviceControllerFragment extends Fragment {


    @BindView(R.id.recycler_view_device_controller)
    RecyclerView recyclerViewDeviceController;
    ArrayList<Device> deviceControllerArrayList;
    ArrayList<String> deviceNameArrayList;
    String selectedRoom;
    @BindView(R.id.roomName)
    TextView roomName;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public DeviceControllerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device_controller, container, false);
        ButterKnife.bind(this, view);
        createDeviceList();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewDeviceController.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapterDeviceController(getActivity(), deviceControllerArrayList);
        recyclerViewDeviceController.setAdapter(adapter);
        return view;
    }

    private void createDeviceList() {
        deviceNameArrayList = ((MainActivity) getActivity()).deviceNameArrayList;
        deviceControllerArrayList = new ArrayList<>();
        selectedRoom = ((MainActivity) getActivity()).roomName;

        for (String s : deviceNameArrayList) {
            Gson gson = new Gson();
            String json = Storage.getDevice(getActivity(), s);
            Device device = gson.fromJson(json, Device.class);
            if (device.getRoomType().equals(selectedRoom)) {
                deviceControllerArrayList.add(device);
            }
        }
    }


}
