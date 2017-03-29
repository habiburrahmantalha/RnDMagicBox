package com.rrmsense.rndmagicbox.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rrmsense.rndmagicbox.R;
import com.rrmsense.rndmagicbox.activities.MainActivity;
import com.rrmsense.rndmagicbox.others.Constants;
import com.rrmsense.rndmagicbox.others.Device;

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
    HashMap<String, Integer> roomImageHashMap;
    String[] roomTypeList = new String[]{"Bed Room", "Dining Room", "Study Room", "Living Room", "Wash Room", "Kitchen"};
    Integer[] roomTypeIconList = {R.drawable.bed_room, R.drawable.dining_room, R.drawable.study_room, R.drawable.living_room, R.drawable.wash_room, R.drawable.kitchen};



    public RecyclerViewAdapterRoom(Context context, HashMap<String, ArrayList<Device>> roomHashMap, ArrayList<String> roomArrayList) {
        this.context = context;
        this.roomHashMap = roomHashMap;
        this.roomArrayList = roomArrayList;
        roomImageHashMap = new HashMap<>();
        for (int i = 0; i < 6; i++) {
            roomImageHashMap.put(roomTypeList[i], roomTypeIconList[i]);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(roomHashMap.size()<=0 || roomArrayList.size() <= 0 || roomHashMap.get(roomArrayList.get(position)).size()<=0 )
            return;

        holder.roomName.setText("Room Name: "+roomArrayList.get(position));
        holder.roomImage.setImageResource(roomImageHashMap.get(roomHashMap.get(roomArrayList.get(position)).get(0).getRoomType()));
        holder.roomType.setText("Room Type: "+roomHashMap.get(roomArrayList.get(position)).get(0).getRoomType());
        holder.deviceCount.setText(roomHashMap.get(roomArrayList.get(position)).size()+ " Device Connected");
    }
    @Override
    public int getItemCount() {
        return roomArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.room_image)
        ImageView roomImage;
        @BindView(R.id.room_name)
        TextView roomName;
        @BindView(R.id.room_type)
        TextView roomType;
        @BindView(R.id.device_count)
        TextView deviceCount;
        @BindView(R.id.card_view_room)
        CardView cardViewRoom;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getScreenWidth()/2, getScreenWidth()/2);
            roomImage.setLayoutParams(layoutParams);
            cardViewRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) context).setRoomName(roomArrayList.get(getAdapterPosition()));
                    ((MainActivity) context).openFragment(Constants.FRAGMENT_DEVICE_CONTROLLER);
                }
            });
        }
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
