package com.sumo.traffic;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dalem on 11/17/2016.
 */

public class DestinationItemAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    private traffic traffic;
    private DestinationActivity destination;

    public DestinationItemAdapter(ArrayList<String> list, Context context, DestinationActivity destination) {
        this.list = list;
        this.context = context;
        this.destination = destination;



    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {

        return list.get(pos);

    }

    @Override
    public long getItemId(int pos) {
        //return list.get(pos).getId();
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.destination_item, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);
        Button editBtn = (Button)view.findViewById(R.id.edit_btn);

    /*    if (traffic.nodelete == 1)
        {
            deleteBtn.setVisibility(View.INVISIBLE);
        }
        else if (traffic.nodelete == 0)
        {
            deleteBtn.setVisibility(View.VISIBLE);
        }*/

        Resources res = context.getResources();
        Drawable drawable = res.getDrawable(R.drawable.edit);
        editBtn.setBackground(drawable);

        Drawable drawable2 = res.getDrawable(R.drawable.delete);
        deleteBtn.setBackground(drawable2);


        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                if (position > 0) {
                    traffic.markers.get(position).remove();

                    traffic.distances.remove(position);
                    traffic.durations.remove(position);
                    traffic.mList.remove(position);
                   traffic.points.remove(position);
                    traffic.markers.remove(position);
                    traffic.timestoStay.remove(position);
                    traffic.reminders.remove(position);

                    list.remove(position);

                    notifyDataSetChanged();
                }
            }
        });



        editBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(destination , poppers.class);
                i.putExtra("currentMarker", position+1);
                destination.startActivityForResult(i, 100);

                //do something
                notifyDataSetChanged();
            }
        });

        return view;
    }
}