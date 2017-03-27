package com.rrmsense.rndmagicbox;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

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
        createDeviceList();


        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewDevice.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapterDevice(getActivity(), deviceArrayList);
        recyclerViewDevice.setAdapter(adapter);
        return view;
    }

    private void createDeviceList() {
        deviceNameArrayList = ((MainActivity)getActivity()).deviceNameArrayList;
        deviceArrayList = new ArrayList<>();
        for(String s: deviceNameArrayList){
            Gson gson = new Gson();
            //Toasty.success(getActivity(),Storage.getDevice(getActivity(),s), Toast.LENGTH_SHORT, true).show();
            String json = Storage.getDevice(getActivity(),s);
            Device device =  gson.fromJson(json, Device.class);;
           /* if(json.equals("")){
                device = new Device(s,"","","");

            }else{
                device = gson.fromJson(json, Device.class);
            }*/

            deviceArrayList.add(device);
        }
    }


}
