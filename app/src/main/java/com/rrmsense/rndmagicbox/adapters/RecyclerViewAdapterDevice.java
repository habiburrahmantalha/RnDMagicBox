package com.rrmsense.rndmagicbox.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rrmsense.rndmagicbox.R;
import com.rrmsense.rndmagicbox.others.Device;
import com.rrmsense.rndmagicbox.others.SpinnerItem;
import com.rrmsense.rndmagicbox.others.Storage;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Talha on 3/25/2017.
 */

public class RecyclerViewAdapterDevice extends RecyclerView.Adapter<RecyclerViewAdapterDevice.ViewHolder> {
    ArrayList<Device> deviceArrayList;
    Context context;

    String[] roomTypeList = new String[]{"Bed Room", "Dinning Room", "THREE", "FOUR"};
    Integer[] roomTypeIconList = {R.drawable.ic_check_black_24dp, R.drawable.ic_check_black_24dp, R.drawable.ic_check_black_24dp, R.drawable.ic_check_black_24dp};
    String[] deviceTypeList = new String[]{"Light", "Fan", "AC", "TV", "Motor"};
    Integer[] deviceTypeIconList = {R.drawable.ic_check_black_24dp, R.drawable.ic_check_black_24dp, R.drawable.ic_check_black_24dp, R.drawable.ic_check_black_24dp, R.drawable.ic_check_black_24dp};


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

        Gson gson = new Gson();
        String json = Storage.getDevice(context, deviceArrayList.get(position).getAddress());
        Device device = gson.fromJson(json, Device.class);
        deviceArrayList.set(position,device);
        holder.address.setText(deviceArrayList.get(position).getAddress());
        holder.deviceName.setText(deviceArrayList.get(position).getDeviceName());
        holder.roomName.setText(deviceArrayList.get(position).getRoomName());

        ArrayList<SpinnerItem> spinnerItemArrayListRoom = new ArrayList<>();
        for (int i = 0; i < roomTypeList.length; i++) {

            SpinnerItem item = new SpinnerItem(roomTypeIconList[i], roomTypeList[i]);
            spinnerItemArrayListRoom.add(item);
        }
        SpinnerAdapterCustom adapterRoom = new SpinnerAdapterCustom(context, R.layout.spinner_item, R.id.name, spinnerItemArrayListRoom);
        holder.roomType.setAdapter(adapterRoom);
        holder.roomType.setSelection(new ArrayList<>(Arrays.asList(roomTypeList)).indexOf(deviceArrayList.get(position).getRoomType()));

        ArrayList<SpinnerItem> spinnerItemArrayListDevice = new ArrayList<>();
        for (int i = 0; i < deviceTypeList.length; i++) {

            SpinnerItem item = new SpinnerItem(deviceTypeIconList[i], deviceTypeList[i]);
            spinnerItemArrayListDevice.add(item);
        }
        SpinnerAdapterCustom adapterDevice = new SpinnerAdapterCustom(context, R.layout.spinner_item, R.id.name, spinnerItemArrayListDevice);
        holder.deviceType.setAdapter(adapterDevice);
        holder.deviceType.setSelection(new ArrayList<>(Arrays.asList(deviceTypeList)).indexOf(deviceArrayList.get(position).getDeviceType()));
    }

    @Override
    public int getItemCount() {
        return deviceArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemSelectedListener, TextView.OnEditorActionListener {

        @BindView(R.id.device_name)
        EditText deviceName;
        @BindView(R.id.device_type)
        AppCompatSpinner deviceType;
        @BindView(R.id.room_type)
        AppCompatSpinner roomType;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.reset)
        Button reset;
        @BindView(R.id.room_name)
        EditText roomName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            deviceType.setOnItemSelectedListener(this);
            roomType.setOnItemSelectedListener(this);
            deviceName.setOnEditorActionListener(this);
            roomName.setOnEditorActionListener(this);
            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gson gson = new Gson();
                    String json = Storage.getDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress());
                    Device device = gson.fromJson(json, Device.class);
                    device.setRoomName("");
                    device.setDeviceName(deviceArrayList.get(getAdapterPosition()).getAddress());
                    device.setStatus(false);
                    device.setUsing(false);
                    device.setDeviceType("");
                    device.setRoomType("");
                    json = gson.toJson(device);
                    Storage.setDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress(), json);
                    notifyItemChanged(getAdapterPosition());

                }
            });

        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Spinner spinner = (Spinner) parent;

            switch (spinner.getId()) {

                case R.id.room_type:
                    if (roomTypeList[position].equals("Select")) {

                    } else {
                        //Toasty.success(context, roomName[position], Toast.LENGTH_SHORT, true).show();
                        Gson gson = new Gson();
                        String json = Storage.getDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress());
                        Device device = gson.fromJson(json, Device.class);
                        device.setRoomType(roomTypeList[position]);
                        json = gson.toJson(device);
                        Storage.setDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress(), json);
                    }
                    break;
                case R.id.device_type:
                    if (deviceTypeList[position].equals("Select")) {

                    } else {
                        //Toasty.success(context, parent.getItemAtPosition(position).toString()+" "+deviceArrayList.get(getAdapterPosition()).getAddress(), Toast.LENGTH_SHORT, true).show();
                        Gson gson = new Gson();
                        String json = Storage.getDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress());
                        Device device = gson.fromJson(json, Device.class);
                        device.setDeviceType(deviceTypeList[position]);
                        json = gson.toJson(device);
                        Storage.setDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress(), json);
                    }
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }


        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


            switch (v.getId()) {
                case R.id.device_name:
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        Gson gson = new Gson();
                        String json = Storage.getDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress());
                        Device device = gson.fromJson(json, Device.class);
                        device.setDeviceName(deviceName.getText().toString());
                        json = gson.toJson(device);
                        Storage.setDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress(), json);

                    }
                    break;
                case R.id.room_name:
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        Gson gson = new Gson();
                        String json = Storage.getDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress());
                        Device device = gson.fromJson(json, Device.class);
                        device.setRoomName(roomName.getText().toString());
                        json = gson.toJson(device);
                        Storage.setDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress(), json);
                    }

                    break;
            }
            return false;
        }
    }
}
