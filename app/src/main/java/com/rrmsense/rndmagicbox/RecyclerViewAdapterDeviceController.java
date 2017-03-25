package com.rrmsense.rndmagicbox;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Talha on 3/25/2017.
 */

class RecyclerViewAdapterDeviceController extends RecyclerView.Adapter<RecyclerViewAdapterDeviceController.ViewHolder> {

    ArrayList<Device> devices;
    Context context;


    public RecyclerViewAdapterDeviceController(Context context, ArrayList<Device> devices) {
        this.context = context;
        this.devices = devices;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_device_controller, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.deviceAddress.setText(devices.get(position).getAddress());
        holder.deviceType.setText(devices.get(position).getType());


    }

    @Override
    public int getItemCount() {
        return devices.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.device_image)
        ImageView deviceImage;
        @BindView(R.id.device_address)
        TextView deviceAddress;
        @BindView(R.id.device_type)
        TextView deviceType;
        @BindView(R.id.device_switch)
        SwitchCompat deviceSwitch;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
