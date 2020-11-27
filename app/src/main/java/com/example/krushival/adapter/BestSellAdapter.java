package com.example.krushival.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.krushival.domain.BestSell;
import com.example.krushival.domain.Category;

import java.util.List;

public class BestSellAdapter extends RecyclerView.Adapter<BestSellAdapter.ViewHolder> {
    private Context context;
    private List<BestSell> mBestSellList;

    public BestSellAdapter(Context context, List<BestSell> mBestSellList) {
        this.context = context;
        this.mBestSellList = mBestSellList;
    }


    @NonNull
    @Override
    public BestSellAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_bestsell_item,parent,false);
        return new BestSellAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestSellAdapter.ViewHolder holder, final int position) {
        holder.bscost.setText(mBestSellList.get(position).getPrice()+"rs");
        holder.bsname.setText(mBestSellList.get(position).getName());
        Glide.with(context).load(mBestSellList.get(position).getImg_url()).into(holder.bsimage);
        holder.bsimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("Detail",mBestSellList.get(position).getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBestSellList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView bsimage;
        TextView bscost,bsname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bsimage = itemView.findViewById(R.id.bs_img);
            bscost = itemView.findViewById(R.id.bs_cost);
            bsname = itemView.findViewById(R.id.bs_name);
        }
    }
}
