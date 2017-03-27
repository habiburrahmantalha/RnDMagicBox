package com.rrmsense.rndmagicbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Talha on 3/27/2017.
 */

public class SpinnerAdapterCustom extends ArrayAdapter<SpinnerItem> {

    ArrayList<SpinnerItem> spinnerItemArrayList;

    LayoutInflater layoutInflater;

    public SpinnerAdapterCustom(Context context, int resource,int textViewId, ArrayList<SpinnerItem> spinnerItemArrayList){
        super(context, resource,textViewId, spinnerItemArrayList);
        this.spinnerItemArrayList = spinnerItemArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return itemView(convertView,position);
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return itemView(convertView,position);
    }


    private View itemView(View convertView, int position) {
        SpinnerItem rowItem = getItem(position);
        viewHolder holder ;
        View itemView = convertView;
        if (itemView==null) {

            holder = new viewHolder();
            layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = layoutInflater.inflate(R.layout.spinner_item, null, false);

            holder.textView = (TextView) itemView.findViewById(R.id.name);
            holder.imageView = (ImageView) itemView.findViewById(R.id.icon);
            itemView.setTag(holder);
        }else{
            holder = (viewHolder) itemView.getTag();
        }

        holder.imageView.setImageResource(rowItem.getIcon());

        holder.textView.setText(rowItem.getName());

        return itemView;

    }


    private class viewHolder{
        TextView textView;
        ImageView imageView;
    }

}
