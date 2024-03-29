package com.example.krushival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.krushival.domain.Items;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    TextView mSubTotal,mTotal,mShip;
    Button payBtn;
    List<Items> itemsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        double amount = 0.0;
        double ship_charge ;
        amount = getIntent().getDoubleExtra("amount",0.0);
        itemsList = (ArrayList<Items>) getIntent().getSerializableExtra("itemsList");
        mSubTotal = findViewById(R.id.sub_total);
        mTotal = findViewById(R.id.total_amt);
        mShip = findViewById(R.id.ship_charge);
        payBtn = findViewById(R.id.pay_btn);

        //mSubTotal.setText(amount+"");

        if(itemsList!=null &&  itemsList.size()>0){
            amount= 0.0;
            for(Items item: itemsList){
                amount+=item.getPrice();
            }
            mSubTotal.setText(amount+" rs");
            double total = 0.0;
            if(amount <= 200){
                ship_charge = 50.0;
                mShip.setText(ship_charge+"");
                total = amount + ship_charge;
                mTotal.setText(total+"rs");
            }
            else {
                ship_charge = 0.0;
                mShip.setText(ship_charge+"");
                total = amount + ship_charge;
                mTotal.setText(total+"rs");
            }

        }else{
            mSubTotal.setText(amount+"");
            double total = 0.0;
            if(amount <= 200){
                ship_charge = 50.0;
                mShip.setText(ship_charge+"");
                total = amount + ship_charge;
                mTotal.setText(total+"rs");
            }
            else {
                ship_charge = 0.0;
                mShip.setText(ship_charge+"");
                total = amount + ship_charge;
                mTotal.setText(total+"rs");
            }
        }



        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(PaymentActivity.this,MainActivity.class));
                Toast.makeText(PaymentActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }
        });

    }
}