package com.example.krushival;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.krushival.adapter.ItemRecyclerAdapter;
import com.example.krushival.domain.Items;
import com.example.krushival.fragment.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Fragment homeFragment;
    private EditText mSearchText;
    private FirebaseFirestore mStore;
    private List<Items> mItemsList;
    private RecyclerView mItemRecyclerView;
    private ItemRecyclerAdapter itemsRecyclerAdapter;
    private Toolbar mToolbar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeFragment=new HomeFragment();
        loadFragment(homeFragment);
        mToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(mToolbar);
        mSearchText = findViewById(R.id.search_text);
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        mItemsList = new ArrayList<>();
        mItemRecyclerView = findViewById(R.id.search_recycler);
        mItemRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        itemsRecyclerAdapter = new ItemRecyclerAdapter(this,mItemsList);
        mItemRecyclerView.setAdapter(itemsRecyclerAdapter);

        mSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    mItemsList.clear();
                    itemsRecyclerAdapter.notifyDataSetChanged();
                }else{
                    searchItem(s.toString());
                }

            }
        });

    }

    private void searchItem(String text) {
        if(!text.isEmpty()){
            mStore.collection("All").whereGreaterThanOrEqualTo("name",text).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful() && task.getResult()!=null){
                                for(DocumentSnapshot doc:task.getResult().getDocuments()){
                                    Items items=doc.toObject(Items.class);
                                    mItemsList.add(items);
                                    itemsRecyclerAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }

    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container,homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logout_btn){
            mAuth.signOut();
            Intent intent=new Intent(HomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(item.getItemId()==R.id.cart){

            Intent intent=new Intent(HomeActivity.this,CartActivity.class);
            startActivity(intent);

        }

        if (item.getItemId()==R.id.add_item)
        {
            Intent intent1=new Intent(HomeActivity.this,AddItemActivity.class);
            startActivity(intent1);
        }

        return true;

    }
}
