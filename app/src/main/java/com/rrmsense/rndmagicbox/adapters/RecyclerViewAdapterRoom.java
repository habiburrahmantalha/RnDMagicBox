package com.rrmsense.rndmagicbox.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rrmsense.rndmagicbox.others.Constants;
import com.rrmsense.rndmagicbox.others.Device;
import com.rrmsense.rndmagicbox.activities.MainActivity;
import com.rrmsense.rndmagicbox.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Talha on 3/25/2017.
 */

public class RecyclerViewAdapterRoom extends RecyclerView.Adapter<RecyclerViewAdapterRoom.ViewHolder> {
    HashMap<String, ArrayList<Device>> roomHashMap;
    ArrayList<String> roomArrayList;
    Context context;



    public RecyclerViewAdapterRoom(Context context, HashMap<String, ArrayList<Device>> roomHashMap, ArrayList<String> roomArrayList) {
        this.context = context;
        this.roomHashMap = roomHashMap;
        this.roomArrayList = roomArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.roomName.setText(roomArrayList.get(position));

       /* RecyclerView.Adapter adapter = new RecyclerViewAdapterDeviceController(context, roomHashMap.get(roomArrayList.get(position)));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        holder.recyclerViewDevice.setLayoutManager(layoutManager);
        holder.recyclerViewDevice.setAdapter(adapter);*/


    }

    @Override
    public int getItemCount() {
        return roomArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.room_name)
        TextView roomName;
        @BindView(R.id.card_view_room)
        CardView cardViewRoom;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardViewRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)context).setRoomName(roomArrayList.get(getAdapterPosition()));
                    ((MainActivity)context).openFragment(Constants.FRAGMENT_DEVICE_CONTROLLER);
                }
            });
        }
    }
}
