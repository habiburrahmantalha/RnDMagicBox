package com.rrmsense.rndmagicbox.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rrmsense.rndmagicbox.R;
import com.rrmsense.rndmagicbox.others.Constants;
import com.rrmsense.rndmagicbox.others.Device;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/**
 * Created by Talha on 3/25/2017.
 */

public class RecyclerViewAdapterDeviceController extends RecyclerView.Adapter<RecyclerViewAdapterDeviceController.ViewHolder> {

    ArrayList<Device> devices;
    Context context;
    HashMap<String, Integer> deviceTypeImageHashMap;
    String[] deviceTypeList = new String[]{Constants.DEFAULT_SPINNER_SELECT,"Light", "Fan", "AC", "TV", "Motor","Geyser"};
    Integer[] deviceTypeIconList = {Constants.DEFAULT_SPINNER_SELECT_IMAGE,R.drawable.light_icon, R.drawable.fan_icon, R.drawable.ac_icon, R.drawable.tv_icon, R.drawable.water_pump,R.drawable.geyser_icon};




    public RecyclerViewAdapterDeviceController(Context context, ArrayList<Device> devices) {
        this.context = context;
        this.devices = devices;
        deviceTypeImageHashMap = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            deviceTypeImageHashMap.put(deviceTypeList[i], deviceTypeIconList[i]);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_device_controller, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.address.setText(devices.get(position).getAddress());
        holder.deviceName.setText(devices.get(position).getDeviceName());
        holder.deviceType.setText(devices.get(position).getDeviceType());
        holder.deviceImage.setImageResource(deviceTypeImageHashMap.get(devices.get(position).getDeviceType()));

    }

    @Override
    public int getItemCount() {
        return devices.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.device_image)
        ImageView deviceImage;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.device_name)
        TextView deviceName;
        @BindView(R.id.device_type)
        TextView deviceType;
        @BindView(R.id.device_switch)
        SwitchCompat deviceSwitch;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            deviceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        Toasty.success(context, "ON", Toast.LENGTH_SHORT).show();
                    }else{
                        Toasty.success(context, "OFF", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}
