package com.example.krushival.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krushival.R;
import com.example.krushival.adapter.CategoryAdapter;
import com.example.krushival.domain.Category;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class HomeFragment extends Fragment {
    private FirebaseFirestore mstore;
    private List<Category> mCategoryList;
    private CategoryAdapter mCategoryAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mstore = FirebaseFirestore.getInstance();
        mCategoryAdapter = new CategoryAdapter(getContext(),mCategoryList);

        mstore.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Category category = document.toObject(Category.class);//store category data
                                mCategoryList.add(category);
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


        return view;
    }
}