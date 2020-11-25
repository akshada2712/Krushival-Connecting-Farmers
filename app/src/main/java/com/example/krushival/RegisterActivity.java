package com.example.krushival;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText name,email,pwd;
    private Button btn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.reg_name);
        email = findViewById(R.id.reg_email);
        pwd = findViewById(R.id.reg_password);
        btn = findViewById(R.id.log_btn);
        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reg_name = name.getText().toString();
                String reg_email = email.getText().toString();
                String reg_pwd = pwd.getText().toString();

                if(!reg_name.isEmpty() && !reg_email.isEmpty() && !reg_pwd.isEmpty()){
                    mAuth.createUserWithEmailAndPassword(reg_email,reg_pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Account Successfully Created",Toast.LENGTH_SHORT).show();
                        }else{
                                Toast.makeText(RegisterActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Please fill up the empty fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signIn(View view) {
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}