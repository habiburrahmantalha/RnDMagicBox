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
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomFragment extends Fragment {


    @BindView(R.id.recycler_view_room)
    RecyclerView recyclerViewRoom;
    HashMap<String, ArrayList<Device> > roomHashMap;
    ArrayList<String> deviceNameArrayList;
    ArrayList<String> roomArrayList;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public RoomFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room, container, false);
        ButterKnife.bind(this, view);
        createDeviceList();


        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewRoom.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapterRoom(getActivity(), roomHashMap, roomArrayList);
        recyclerViewRoom.setAdapter(adapter);
        return view;
    }

    private void createDeviceList() {
        deviceNameArrayList = ((MainActivity)getActivity()).deviceNameArrayList;
        roomArrayList = new ArrayList<>();
        roomHashMap = new HashMap<>();
        for(String s: deviceNameArrayList){
            Gson gson = new Gson();
            String json = Storage.getDevice(getActivity(),s);
            Device device;
            if(json.equals("")){

            }else{
                device = gson.fromJson(json, Device.class);
                if(device.getRoom()!=null && roomHashMap.containsKey(device.getRoom())){
                    roomHashMap.get(device.getRoom()).add(device);

                }else{
                    roomArrayList.add(device.getRoom());
                    roomHashMap.put(device.getRoom(),new ArrayList<Device>());
                    roomHashMap.get(device.getRoom()).add(device);
                }
            }
            //roomArrayList.add(new Room("",device))

        }
    }

}
