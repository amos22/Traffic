package com.sumo.traffic;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sumo.traffic.AreaFilter.PlacesFilters;
import com.sumo.traffic.AreaFilter.agency;
import com.sumo.traffic.AreaFilter.placestogo;
import com.sumo.traffic.AreaFilter.store;
import com.sumo.traffic.AreaFilter.usersneed;

import java.util.List;

/**
 * Created by sasank on 26-11-2016.
 */

public class VerticalListAdapter extends RecyclerView.Adapter<VerticalListAdapter.MyViewHolder>{

    private Context mContext;
    private List<Model> mModelList;

    public VerticalListAdapter(Context context, List<Model> list) {
        this.mContext = context;
        this.mModelList = list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.modal_list_item, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.txtView.setText(mModelList.get(position).getName());
        holder.imgView.setImageDrawable(mContext.getResources().getDrawable(mModelList.get(position).getImgId()));
/*        holder.imgView2.setImageDrawable(mContext.getResources().getDrawable(mModelList.get(position).getImgId()));*/
    }

    @Override
    public int getItemCount()
    {
        return mModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtView;
        public ImageButton imgView;


        public MyViewHolder(View view) {
            super(view);

            final  Context context;

            final Intent[] intent = new Intent[5];
            context = itemView.getContext();
            txtView = (TextView) view.findViewById(R.id.txtView);
            imgView = (ImageButton) view.findViewById(R.id.imgView);

            imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (getAdapterPosition() == 0)
                            {
                                intent[0] =  new Intent(context, radiusplace.class);
                                context.startActivity(intent[0]);


                            }

                            if (getAdapterPosition() == 1)
                            {
                                intent[1] =  new Intent(context, agency.class);
                                context.startActivity(intent[1]);


                            }
                           else if (getAdapterPosition() == 2)
                            {
                                intent[1] =  new Intent(context, placestogo.class);
                                context.startActivity(intent[1]);


                            }
                            else if (getAdapterPosition() == 3)
                            {
                                intent[2] =  new Intent(context, usersneed.class);
                                context.startActivity(intent[2]);


                            }
                            else if (getAdapterPosition() == 4)
                            {
                                intent[3] =  new Intent(context, store.class);
                                context.startActivity(intent[3]);


                            }

                            else if (getAdapterPosition() == 5)
                            {
                                intent[4] =  new Intent(context, PlacesFilters.class);
                                context.startActivity(intent[4]);


                            }
                        }
                    });
                }
            });



        }
    }


}


