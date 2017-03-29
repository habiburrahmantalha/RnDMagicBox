package com.rrmsense.rndmagicbox.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.rrmsense.rndmagicbox.R;
import com.rrmsense.rndmagicbox.activities.MainActivity;
import com.rrmsense.rndmagicbox.adapters.RecyclerViewAdapterDevice;
import com.rrmsense.rndmagicbox.others.Device;
import com.rrmsense.rndmagicbox.others.Storage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeviceFragment extends Fragment {


    @BindView(R.id.recycler_view_device)
    RecyclerView recyclerViewDevice;
    ArrayList<Device> deviceArrayList;
    ArrayList<String> deviceNameArrayList;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public DeviceFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_device, container, false);
        ButterKnife.bind(this, view);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewDevice.setLayoutManager(layoutManager);
        deviceArrayList = new ArrayList<>();
        adapter = new RecyclerViewAdapterDevice(getActivity(), deviceArrayList);
        recyclerViewDevice.setAdapter(adapter);
        createDeviceList();
        return view;
    }

    private void createDeviceList() {
        deviceNameArrayList = ((MainActivity)getActivity()).deviceNameArrayList;
        for(String s: deviceNameArrayList){
            Gson gson = new Gson();
            String json = Storage.getDevice(getActivity(),s);
            Device device =  gson.fromJson(json, Device.class);;
            deviceArrayList.add(device);
            adapter.notifyItemInserted(deviceArrayList.size()-1);
        }
    }


}
