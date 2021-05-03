package com.example.krushival;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krushival.adapter.CartAdapter;
import com.example.krushival.domain.Items;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.TargetChangeOrBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.ItemRemoved {
    FirebaseFirestore mStore ;
    FirebaseAuth mAuth;
    List<Items> itemsList;
    RecyclerView cartRecyclerView;
    CartAdapter cartAdapter ;
    Button buyItemButton;
    TextView totalAmount;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        itemsList = new ArrayList<>();
        cartRecyclerView = findViewById(R.id.cart_item_container);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setHasFixedSize(true);
        mToolbar = findViewById(R.id.cart_toolbar);

        cartAdapter = new CartAdapter(itemsList,this);
        cartRecyclerView.setAdapter(cartAdapter);
        buyItemButton = (Button) findViewById(R.id.cart_item_buy_now);
        totalAmount = (TextView) findViewById(R.id.total_amount_cart);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        buyItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartActivity.this,AddressActivity.class);
                intent.putExtra("itemsList", (Serializable) itemsList);
                startActivity(intent);

            }
        });

        cartAdapter = new CartAdapter(itemsList,this);
        cartRecyclerView.setAdapter(cartAdapter);

        mStore.collection("User").document(mAuth.getCurrentUser().getUid())
                .collection("Cart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                 if(task.isSuccessful()){
                     if(task.getResult()!=null){
                         for(DocumentChange doc : task.getResult().getDocumentChanges()) {
                             Items item = doc.getDocument().toObject(Items.class);
                             String documentId = doc.getDocument().getId();
                             item.setDocId(documentId);
                             itemsList.add(item);
                         }
                         calculateAmount(itemsList);
                         cartAdapter.notifyDataSetChanged();
                     }
                    else{
                         Toast.makeText(CartActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                     }
                 }
            }
        });

    }

    private void calculateAmount(List<Items> itemsList) {
        double totalAmountInDouble = 0.0;
        for(Items item:itemsList){
            totalAmountInDouble += item.getPrice();
        }
        totalAmount.setText("Total Amount : "+totalAmountInDouble+" Rs");
    }

    @Override
    public void onItemRemoved(List<Items> itemsList) {
        calculateAmount(itemsList);
    }
}