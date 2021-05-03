package com.example.krushival;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.krushival.domain.BestSell;
import com.example.krushival.domain.Feature;
import com.example.krushival.domain.Items;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailActivity extends AppCompatActivity {
    private ImageView mImage;
    private TextView mItemName;
    private TextView mPrice;
    private Button mItemRating;
    private TextView mItemRatDesc;
    private TextView mItemDesc;
    private Button mAddToCart;
    private Button mBuyBtn;
    Feature feature = null;
    BestSell bestSell = null;
    Items items=null;
    private Toolbar mToolbar;
    FirebaseFirestore mStore;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setContentView(R.layout.activity_detail);


        mImage=findViewById(R.id.item_img);
        mItemName=findViewById(R.id.item_name);
        mPrice=findViewById(R.id.item_price);
        mItemRating=findViewById(R.id.item_rating);
        mItemRatDesc=findViewById(R.id.item_rat_des);
        mItemDesc=findViewById(R.id.item_des);
        mAddToCart=findViewById(R.id.item_add_cart);
        mBuyBtn=findViewById(R.id.item_buy_now);
        mToolbar = findViewById(R.id.detail_toolbar);
        mStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Object obj=  getIntent().getSerializableExtra("Detail");

        if(obj instanceof Feature){
            feature= (Feature) obj;
        }else if(obj instanceof BestSell){
            bestSell= (BestSell) obj;
        }
        else if(obj instanceof Items){
            items = (Items) obj;
        }


        if(feature!=null){
            Glide.with(getApplicationContext()).load(feature.getImg_url()).into(mImage);
            mItemName.setText(feature.getName());
            mPrice.setText(feature.getPrice()+"rs");
            mItemRating.setText(feature.getRating()+"");
            if(feature.getRating()>3){
                mItemRatDesc.setText("Very Good");
            }else{
                mItemRatDesc.setText("Good");
            }
            mItemDesc.setText(feature.getDescription());
        }
        if(bestSell!=null){
            Glide.with(getApplicationContext()).load(bestSell.getImg_url()).into(mImage);
            mItemName.setText(bestSell.getName());
            mPrice.setText(bestSell.getPrice()+"rs");
            mItemRating.setText(bestSell.getRating()+"");
            if(bestSell.getRating()>3){
                mItemRatDesc.setText("Very Good");
            }else{
                mItemRatDesc.setText("Good");
            }
            mItemDesc.setText(bestSell.getDescription());
        }

        if(items!=null){
            Glide.with(getApplicationContext()).load(items.getImg_url()).into(mImage);
            mItemName.setText(items.getName());
            mPrice.setText(items.getPrice()+"rs");
            mItemRating.setText(items.getRating()+"");
            if(items.getRating()>3){
                mItemRatDesc.setText("Very Good");
            }else{
                mItemRatDesc.setText("Good");
            }
            mItemDesc.setText(items.getDescription());
        }

        mAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(feature!=null){
                    mStore.collection("User").document(mAuth.getCurrentUser().getUid())
                            .collection("Cart").add(feature).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(DetailActivity.this,"Item added to cart",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if(bestSell!=null){
                    mStore.collection("User").document(mAuth.getCurrentUser().getUid())
                            .collection("Cart").add(bestSell).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(DetailActivity.this,"Item added to cart",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if(items!=null){
                    mStore.collection("User").document(mAuth.getCurrentUser().getUid())
                            .collection("Cart").add(items).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(DetailActivity.this,"Item added to cart",Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });

        mBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,AddressActivity.class);
                if(feature!=null){
                    intent.putExtra("item",feature);
                }
                if(bestSell!=null){
                    intent.putExtra("item",bestSell);
                }
                if(items!=null){
                    intent.putExtra("item",items);
                }
                startActivity(intent);
            }
        });




    }
}