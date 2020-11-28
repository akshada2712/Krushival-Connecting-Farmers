package com.example.krushival.adapter;

import android.content.Context;
import android.content.Intent;
import android.nfc.TagLostException;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.krushival.DetailActivity;
import com.example.krushival.R;
import com.example.krushival.domain.Feature;

import java.util.List;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.ViewHolder> {
    Context context;
    List<Feature> mFeatureList;

    public FeatureAdapter(Context context, List<Feature> mFeatureList) {
        this.context = context;
        this.mFeatureList = mFeatureList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_feature_item,parent,false);
        return new FeatureAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mFetcost.setText(mFeatureList.get(position).getPrice()+"rs");
        holder.mFetname.setText(mFeatureList.get(position).getName());
        Glide.with(context).load(mFeatureList.get(position).getImage_url()).into(holder.mFetImage);
        holder.mFetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Detail",mFeatureList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mFeatureList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mFetImage;
        TextView mFetcost , mFetname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mFetImage = itemView.findViewById(R.id.f_img);
            mFetcost = itemView.findViewById(R.id.f_cost);
            mFetname = itemView.findViewById(R.id.f_name);


        }
    }
}
