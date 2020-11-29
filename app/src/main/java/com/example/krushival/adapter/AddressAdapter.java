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
    private RadioButton mSelectedRadioBtutton;
    public AddressAdapter(Context applicationContext, List<Address> mAddressList) {
        this.applicationContext=applicationContext;
        this.mAddressList=mAddressList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(applicationContext).inflate(R.layout.single_address_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mAddress.setText(mAddressList.get(position).getAddress());
        holder.mRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Address address:mAddressList){
                    address.setSelected(false);
                }
                mAddressList.get(position).setSelected(true);

                if(mSelectedRadioBtutton!=null){
                    mSelectedRadioBtutton.setChecked(false);
                }
                mSelectedRadioBtutton = (RadioButton) v;
                mSelectedRadioBtutton.setChecked(true);
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
            mAddress=itemView.findViewById(R.id.address_add);
            mRadio=itemView.findViewById(R.id.select_address);
        }
    }
}
