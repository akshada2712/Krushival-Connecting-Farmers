package com.example.krushival;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.krushival.adapter.ItemRecyclerAdapter;
import com.example.krushival.domain.Items;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class ItemsActivity extends AppCompatActivity {
    private FirebaseFirestore mStore;
    List<Items> mItemsList;
    private RecyclerView itemRecyclerview ;
    private ItemRecyclerAdapter itemsRecyclerAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        String type = getIntent().getStringExtra("type");


        mStore = FirebaseFirestore.getInstance();
        mItemsList = new ArrayList<>();
        itemRecyclerview = findViewById(R.id.items_recycler);
        mToolbar = findViewById(R.id.item_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Items");

        itemsRecyclerAdapter = new ItemRecyclerAdapter(this,mItemsList);
        itemRecyclerview.setLayoutManager(new GridLayoutManager(this,2));
        itemRecyclerview.setAdapter(itemsRecyclerAdapter);


        if(type==null || type.isEmpty()){
            mStore.collection("All").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc:task.getResult().getDocuments()){
                            Items items = doc.toObject(Items.class);
                            mItemsList.add(items);
                            itemsRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if(type!=null && type.equalsIgnoreCase("vegetable")){
            mStore.collection("All").whereEqualTo("type","vegetable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc:task.getResult().getDocuments()){
                            Items items = doc.toObject(Items.class);
                            mItemsList.add(items);
                            itemsRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if(type!= null && type.equalsIgnoreCase("fruit")){
            mStore.collection("All").whereEqualTo("type","fruit").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc:task.getResult().getDocuments()){
                            Items items = doc.toObject(Items.class);
                            mItemsList.add(items);
                            itemsRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if(type!= null && type.equalsIgnoreCase("dairy")){
            mStore.collection("All").whereEqualTo("type","dairy").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc:task.getResult().getDocuments()){
                            Items items = doc.toObject(Items.class);
                            mItemsList.add(items);
                            itemsRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem item= menu.findItem(R.id.search_it);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchItem(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }*/

    /*private void searchItem(String newText) {
        mItemsList.clear();
        mStore.collection("All").whereGreaterThanOrEqualTo("name",newText).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot doc:task.getResult().getDocuments()){
                        Log.i("TAG", "onComplete: "+doc.toString());
                        Items items=doc.toObject(Items.class);
                        mItemsList.add(items);
                        itemsRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }*/
}
