package com.rrmsense.rndmagicbox;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/**
 * Created by Talha on 3/25/2017.
 */

class RecyclerViewAdapterDevice extends RecyclerView.Adapter<RecyclerViewAdapterDevice.ViewHolder> {
    ArrayList<Device> deviceArrayList;
    Context context;
    String[] roomName = new String[]{"ONE", "TWO", "THREE", "FOUR"};
    Integer[] roomIcon = {R.drawable.ic_check_black_24dp, R.drawable.ic_check_black_24dp, R.drawable.ic_check_black_24dp, R.drawable.ic_check_black_24dp};
    String[] deviceTypeName = new String[]{"Light", "Fan", "AC","TV", "Motor"};
    Integer[] deviceTypeIcon = {R.drawable.ic_check_black_24dp, R.drawable.ic_check_black_24dp, R.drawable.ic_check_black_24dp, R.drawable.ic_check_black_24dp,R.drawable.ic_check_black_24dp};


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

        holder.deviceName.setText(deviceArrayList.get(position).getDeviceName());



        ArrayList<SpinnerItem> spinnerItemArrayListRoom = new ArrayList<>();
        for (int i = 0; i < roomName.length; i++) {

            SpinnerItem item = new SpinnerItem(roomIcon[i], roomName[i]);
            spinnerItemArrayListRoom.add(item);
        }
        SpinnerAdapterCustom adapterRoom = new SpinnerAdapterCustom(context, R.layout.spinner_item, R.id.name, spinnerItemArrayListRoom);
        holder.deviceRoom.setAdapter(adapterRoom);
        holder.deviceRoom.setSelection(new ArrayList<>(Arrays.asList(roomName)).indexOf(deviceArrayList.get(position).getRoom()));

        ArrayList<SpinnerItem> spinnerItemArrayListDevice = new ArrayList<>();
        for (int i = 0; i < deviceTypeName.length; i++) {

            SpinnerItem item = new SpinnerItem(deviceTypeIcon[i], deviceTypeName[i]);
            spinnerItemArrayListDevice.add(item);
        }
        SpinnerAdapterCustom adapterDevice = new SpinnerAdapterCustom(context, R.layout.spinner_item, R.id.name, spinnerItemArrayListDevice);
        holder.deviceType.setAdapter(adapterDevice);
        holder.deviceType.setSelection(new ArrayList<>(Arrays.asList(deviceTypeName)).indexOf(deviceArrayList.get(position).getType()));


        //holder.deviceTypeName.setSelection(new ArrayList<>(Arrays.asList(device)).indexOf(deviceArrayList.get(position).getType()));
        //holder.deviceRoom.setSelection(new ArrayList<>(Arrays.asList(room)).indexOf(deviceArrayList.get(position).getRoom()));


    }

    @Override
    public int getItemCount() {
        return deviceArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemSelectedListener {

        @BindView(R.id.device_name)
        EditText deviceName;
        @BindView(R.id.device_type)
        AppCompatSpinner deviceType;
        @BindView(R.id.device_room)
        AppCompatSpinner deviceRoom;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            deviceType.setOnItemSelectedListener(this);
            deviceRoom.setOnItemSelectedListener(this);
            deviceName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    switch (actionId) {
                        case EditorInfo.IME_ACTION_DONE:


                            break;
                    }
                    return false;
                }
            });

        }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Spinner spinner = (Spinner) parent;

            switch (spinner.getId()) {

                case R.id.device_room:
                    if (roomName[position].equals("Select")) {

                    } else {
                        Toasty.success(context, roomName[position], Toast.LENGTH_SHORT, true).show();
                        Gson gson = new Gson();
                        String json = Storage.getDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress());
                        Device device = gson.fromJson(json, Device.class);
                        ;
                       /* if(json.equals("")){
                            device = new Device(deviceArrayList.get(getAdapterPosition()).getAddress(),"","","");
                        }else{
                            device = gson.fromJson(json, Device.class);
                        }*/
                        device.setRoom(roomName[position]);
                        json = gson.toJson(device);
                        Storage.setDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress(), json);
                    }
                    break;
                case R.id.device_type:
                    if (parent.getItemAtPosition(position).toString().equals("Select Device Type")) {

                    } else {
                        //Toasty.success(context, parent.getItemAtPosition(position).toString()+" "+deviceArrayList.get(getAdapterPosition()).getAddress(), Toast.LENGTH_SHORT, true).show();
                        Gson gson = new Gson();
                        String json = Storage.getDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress());
                        Device device = gson.fromJson(json, Device.class);
                        ;
                      /*  if(json.equals("")){
                            device = new Device(deviceArrayList.get(getAdapterPosition()).getAddress(),"","","");
                        }else{
                            device = gson.fromJson(json, Device.class);
                        }*/

                        device.setType(parent.getItemAtPosition(position).toString());
                        json = gson.toJson(device);
                        Storage.setDevice(context, deviceArrayList.get(getAdapterPosition()).getAddress(), json);
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
