package com.example.krushival;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.krushival.adapter.AddressAdapter;
import com.example.krushival.domain.Address;
import com.example.krushival.domain.BestSell;
import com.example.krushival.domain.Category;
import com.example.krushival.domain.Feature;
import com.example.krushival.domain.Items;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {
    private RecyclerView mAddressRecyclerview;
    private AddressAdapter mAddressAdapter;
    private Button mAddAddress , paymentBtn;
    private List<Address> mAddressList;
    private FirebaseFirestore mStore;
    private FirebaseAuth mAuth;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        final Object obj = getIntent().getSerializableExtra("item");
        mStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mAddressRecyclerview = findViewById(R.id.address_recycler);
        mAddAddress = findViewById(R.id.add_address_btn);
        paymentBtn = findViewById(R.id.payment_btn);
        mToolbar = findViewById(R.id.address_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAddressList = new ArrayList<>();
        mAddressAdapter = new AddressAdapter(getApplicationContext(),mAddressList);
        mAddressRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        mAddressRecyclerview.setAdapter(mAddressAdapter);

        mStore.collection("User").document(mAuth.getCurrentUser().getUid())
                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot doc:task.getResult().getDocuments()){
                        Address address=doc.toObject(Address.class);
                        Log.i("Address", "LOG::::::"+address.getAddress()); //where getAddress() is your function defined in model class
                        mAddressList.add(address);
                        mAddressAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


        mAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this,AddAddressActivity.class);
                startActivity(intent);

            }
        });

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount = 0.0;
                if(obj instanceof Feature){
                    Feature f = (Feature) obj;
                    amount = f.getPrice();

                }
                if(obj instanceof BestSell){
                    BestSell b = (BestSell) obj;
                    amount = b.getPrice();
                }
                if(obj instanceof Items){
                    Items i = (Items) obj;
                    amount = i.getPrice();
                }
                Intent intent = new Intent(AddressActivity.this,PaymentActivity.class);
                intent.putExtra("amount",amount);
                startActivity(intent);
            }
        });

    }
}