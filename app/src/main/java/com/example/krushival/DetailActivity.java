package com.example.krushival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.krushival.domain.BestSell;
import com.example.krushival.domain.Feature;

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
    //Items items=null;
    private Toolbar mToolbar;



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
        Object obj=  getIntent().getSerializableExtra("Detail");

        if(obj instanceof Feature){
            feature= (Feature) obj;
        }else if(obj instanceof BestSell){
            bestSell= (BestSell) obj;
        }


        if(feature!=null){
            Glide.with(getApplicationContext()).load(feature.getImage_url()).into(mImage);
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

        mAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,AddressActivity.class);
                startActivity(intent);

            }
        });



    }
}