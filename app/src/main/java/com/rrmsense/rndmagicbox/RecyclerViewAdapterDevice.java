package com.rrmsense.rndmagicbox;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Talha on 3/25/2017.
 */

class RecyclerViewAdapterDevice extends RecyclerView.Adapter<RecyclerViewAdapterDevice.ViewHolder> {
    ArrayList<Device> deviceArrayList;
    Context context;


    public RecyclerViewAdapterDevice(Context context, ArrayList<Device> deviceArrayList) {
        this.deviceArrayList = deviceArrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_device_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.deviceAddress.setText(deviceArrayList.get(position).getAddress());
        String[] device = context.getResources().getStringArray (R.array.array_device_type);
        String[] room = context.getResources().getStringArray (R.array.array_room_number);
        holder.deviceType.setSelection(new ArrayList<>(Arrays.asList(device)).indexOf(deviceArrayList.get(position).getType()));
        holder.deviceRoom.setSelection(new ArrayList<>(Arrays.asList(room)).indexOf(deviceArrayList.get(position).getRoom()));


    }

    @Override
    public int getItemCount() {
        return deviceArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, AdapterView.OnItemSelectedListener {

        @BindView(R.id.device_address)
        TextView deviceAddress;
        @BindView(R.id.device_type)
        AppCompatSpinner deviceType;
        @BindView(R.id.device_room)
        AppCompatSpinner deviceRoom;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            deviceType.setOnItemSelectedListener(this);
            deviceRoom.setOnItemSelectedListener(this);

        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Spinner spinner = (Spinner) parent;

            switch (spinner.getId()) {

                case R.id.device_room:
                    if (parent.getItemAtPosition(position).toString().equals("Select Room")) {

                    } else {
                        //Toasty.success(context, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT, true).show();
                        Gson gson = new Gson();
                        String json = Storage.getDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress());
                        Device device;
                        if(json.equals("")){
                            device = new Device(deviceArrayList.get(getAdapterPosition()).getAddress(),"","","");
                        }else{
                            device = gson.fromJson(json, Device.class);
                        }
                        device.setRoom(parent.getItemAtPosition(position).toString());
                        json = gson.toJson(device);
                        Storage.setDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress(),json);
                    }
                    break;
                case R.id.device_type:
                    if (parent.getItemAtPosition(position).toString().equals("Select Device Type")) {

                    } else {
                        //Toasty.success(context, parent.getItemAtPosition(position).toString()+" "+deviceArrayList.get(getAdapterPosition()).getAddress(), Toast.LENGTH_SHORT, true).show();
                        Gson gson = new Gson();
                        String json = Storage.getDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress());
                        Device device;
                        if(json.equals("")){
                            device = new Device(deviceArrayList.get(getAdapterPosition()).getAddress(),"","","");
                        }else{
                            device = gson.fromJson(json, Device.class);
                        }

                        device.setType(parent.getItemAtPosition(position).toString());
                        json = gson.toJson(device);
                        Storage.setDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress(),json);
                        //Toasty.success(context,Storage.getDevice(context,deviceArrayList.get(getAdapterPosition()).getAddress()), Toast.LENGTH_SHORT, true).show();
                    }
                    break;
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
