package com.example.krushival.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.krushival.R;
import com.example.krushival.domain.Items;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    List<Items> itemsList;
    ItemRemoved itemRemoved;
    public CartAdapter(List<Items> itemsList,ItemRemoved itemRemoved){
        this.itemsList = itemsList;
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        this.itemRemoved = itemRemoved;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_cart,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.cartName.setText(itemsList.get(position).getName());
        holder.cartPrice.setText("Rs "+itemsList.get(position).getPrice());
        Glide.with(holder.itemView.getContext()).load(itemsList.get(position).getImg_url()).into(holder.cartImage);
        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection("User").document(firebaseAuth.getCurrentUser().getUid())
                        .collection("Cart").document(itemsList.get(position).getDocId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            itemsList.remove(itemsList.get(position));
                            notifyDataSetChanged();
                            itemRemoved.onItemRemoved(itemsList);
                            Toast.makeText(holder.itemView.getContext(),"Item Removed",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(holder.itemView.getContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cartImage;
        TextView cartName,cartPrice,removeItem;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            cartImage = itemView.findViewById(R.id.cart_image);
            cartName = itemView.findViewById(R.id.cart_name);
            cartPrice = itemView.findViewById(R.id.cart_price);
            removeItem = itemView.findViewById(R.id.remove_item);

        }
    }
    public interface ItemRemoved{
        public void onItemRemoved(List<Items> itemsList);
    }
}
