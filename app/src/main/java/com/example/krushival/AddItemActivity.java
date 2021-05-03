package com.example.krushival;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.krushival.domain.Additem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.annotation.Nullable;


public class AddItemActivity extends AppCompatActivity  {
    public EditText name;
    public EditText price;
    public EditText description;
    public EditText rating;
    public ImageView img_url;
    public RadioGroup type;
    public FirebaseAuth mAuth;
    public FirebaseFirestore db;
    public Button Submit;
    public RadioButton vegetable,fruit,dairy;
    public Uri uri;
    public static final int GALLERY_REQUEST_CODE = 2345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_item);
        name = (EditText) findViewById(R.id.name);
        price = (EditText) findViewById(R.id.price);
        description = (EditText) findViewById(R.id.description);
        rating = (EditText) findViewById(R.id.rating);
        img_url = (ImageView) findViewById(R.id.img_url);
        type = (RadioGroup) findViewById(R.id.type);
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        Submit = (Button) findViewById(R.id.submit);
        vegetable = (RadioButton) findViewById(R.id.vegetable);
        fruit = (RadioButton) findViewById(R.id.fruit);
        dairy = (RadioButton) findViewById(R.id.dairy);
        img_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
            }
        });


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processinsert();

            }




        });
    }
    private boolean validateInputs(String name,  String description)
    {
        if (name.isEmpty()) {
            Toast.makeText(AddItemActivity.this,"insert name of product",Toast.LENGTH_SHORT).show();
            return true;
        }

        if(description.isEmpty())
        {
            Toast.makeText(AddItemActivity.this,"insert description  of product",Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }
    public void processinsert(){
        String name1 = name.getText().toString().trim();

        String desc = description.getText().toString().trim();
        double price1=Double.parseDouble(price.getText().toString());
        int rating1=Integer.parseInt(rating.getText().toString());
        String p1=vegetable.getText().toString();
        String p2=fruit.getText().toString();
        String p3=dairy.getText().toString();
        final String URL=uri.toString();
        if (!validateInputs(name1,  desc)) {
            CollectionReference dbProducts = db.collection("All");
            if(vegetable.isChecked()) {
                Additem additem = new Additem(URL,name1, price1, desc, rating1,p1);
                dbProducts.add(additem).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddItemActivity.this, "Product Added", Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddItemActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            if(fruit.isChecked()) {
                Additem additem2 = new Additem(URL,name1, price1, desc, rating1,p2);
                dbProducts.add(additem2).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddItemActivity.this, "Product Added", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddItemActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            if(dairy.isChecked()) {
                Additem additem3 = new Additem(URL,name1, price1, desc, rating1,p3);
                dbProducts.add(additem3).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddItemActivity.this, "Product Added", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddItemActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }

    }



    public void onActivityResult(int requestCode,int resultCode,@Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (GALLERY_REQUEST_CODE==requestCode && resultCode==RESULT_OK)
        {
            uri=data.getData();
            img_url.setImageURI(uri);
        }
    }
}