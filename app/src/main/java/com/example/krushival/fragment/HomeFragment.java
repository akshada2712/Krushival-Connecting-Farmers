package com.example.krushival.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krushival.R;
import com.example.krushival.adapter.BestSellAdapter;
import com.example.krushival.adapter.CategoryAdapter;
import com.example.krushival.adapter.FeatureAdapter;
import com.example.krushival.domain.BestSell;
import com.example.krushival.domain.Category;
import com.example.krushival.domain.Feature;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private FirebaseFirestore mstore;
    //Category Tab
    private List<Category> mCategoryList;
    private CategoryAdapter mCategoryAdapter;
    private RecyclerView mCatRecyclerview;

    //Featured Tab,define
    private List<Feature> mFeatureList;
    private FeatureAdapter mFeatureAdapter;
    private RecyclerView mFeatureRecyclerview;

    //BestSell tab define
    private List<BestSell> mBestSellList;
    private BestSellAdapter mBestSellAdapter;
    private RecyclerView mBestSellRecyclerview;



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mstore = FirebaseFirestore.getInstance();
        //Category
        mCatRecyclerview = view.findViewById(R.id.category_recycler);
        mCategoryList = new ArrayList<>();
        mCategoryAdapter = new CategoryAdapter(getContext(),mCategoryList);
        mCatRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        mCatRecyclerview.setAdapter(mCategoryAdapter);//kept and displayed the data

        //Feature
        mFeatureRecyclerview = view.findViewById(R.id.feature_recycler);
        mFeatureList = new ArrayList<>();
        mFeatureAdapter = new FeatureAdapter(getContext(),mFeatureList);
        mFeatureRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        mFeatureRecyclerview.setAdapter(mFeatureAdapter);

        //Best Sell
        mBestSellRecyclerview = view.findViewById(R.id.bestsell_recycler);
        mBestSellList = new ArrayList<>();
        mBestSellAdapter = new BestSellAdapter(getContext(),mBestSellList);
        mBestSellRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        mBestSellRecyclerview.setAdapter(mBestSellAdapter);

        //Reading from category
        mstore.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Category category = document.toObject(Category.class);//store category data
                                mCategoryList.add(category);
                                mCategoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            mstore.collection("Category")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                for (QueryDocumentSnapshot document : task.getResult()) {

                                                }
                                            } else {
                                                Log.w("Tag", "Error getting documents.", task.getException());
                                            }
                                        }
                                    });
                        }
                    }
                });


        //Reading from feature
        mstore.collection("Feature")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Feature feature;//store feature data
                                feature = document.toObject(Feature.class);
                                mFeatureList.add(feature);
                                mFeatureAdapter.notifyDataSetChanged();
                            }
                        } else {
                            mstore.collection("Feature")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                for (QueryDocumentSnapshot document : task.getResult()) {

                                                }
                                            } else {
                                                Log.w("Tag", "Error getting documents.", task.getException());
                                            }
                                        }
                                    });
                        }
                    }
                });

        //Reading from BestSell
        mstore.collection("BestSell")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                BestSell bestSell;//store feature data
                                bestSell = document.toObject(BestSell.class);
                                mBestSellList.add(bestSell);
                                mBestSellAdapter.notifyDataSetChanged();
                            }
                        } else {
                            mstore.collection("BestSell")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                for (QueryDocumentSnapshot document : task.getResult()) {

                                                }
                                            } else {
                                                Log.w("Tag", "Error getting documents.", task.getException());
                                            }
                                        }
                                    });
                        }
                    }
                });


        return view;
    }
}