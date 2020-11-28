package com.example.krushival.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krushival.R;
import com.example.krushival.domain.Address;

import org.w3c.dom.Text;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    Context applicationContext;
    List<Address> mAddressList;
    public AddressAdapter(Context applicationContext, List<Address> mAddressList) {
        this.applicationContext = applicationContext;
        this.mAddressList = mAddressList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(applicationContext).inflate(R.layout.single_address_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mAddress.setText(mAddressList.get(position).getAddress());

        holder.mRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(applicationContext,"Checked",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mAddressList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mAddress;
        private RadioButton mRadio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAddress = itemView.findViewById(R.id.address_add);
            mRadio = itemView.findViewById(R.id.select_address);
        }
    }

}
