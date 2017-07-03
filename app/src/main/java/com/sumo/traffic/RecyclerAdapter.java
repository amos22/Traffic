package com.sumo.traffic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alessio on 13/01/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<String> listAuthorNames = new ArrayList<>(), listAuthorTexts = new ArrayList<>();
    private Map<String, String> mapProfilePhotoUrl = new HashMap<>();
    private List<Integer> listReviewRating = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView authorName, authorText, authorRating;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            this.authorName = (TextView) view.findViewById(R.id.author_name);
            this.authorText = (TextView) view.findViewById(R.id.author_text);
            this.authorRating = (TextView) view.findViewById(R.id.author_rating);
            imageView = (ImageView) view.findViewById(R.id.user_profile_picture);
        }

    }

    public RecyclerAdapter(List<String> authorNames, Map<String, String> profilePhotoUrl, List<String> authorTexts, List<Integer> rating) {
        this.listAuthorNames = authorNames;
        this.listAuthorTexts = authorTexts;
        this.listReviewRating = rating;
        this.mapProfilePhotoUrl = profilePhotoUrl;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int adapterPosition = holder.getAdapterPosition();
        holder.authorText.setText(listAuthorTexts.get(adapterPosition));
        holder.authorRating.setText(String.format("Rating: %d", listReviewRating.get(position)));
        holder.authorName.setText(listAuthorNames.get(adapterPosition));
        if (mapProfilePhotoUrl.size() > adapterPosition) {
            String url = mapProfilePhotoUrl.get(listAuthorNames.get(adapterPosition));
            if(url == null) return;
            Picasso.with(holder.imageView.getContext()).load(url).into(holder.imageView);

        }
    }

    @Override
    public int getItemCount() {
        int count = listAuthorNames.size();
        return count;
    }
}
