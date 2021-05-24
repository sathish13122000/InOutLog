package com.example.inoutlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HodLoginpage extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    FirebaseAuth fAuth;
    private Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod_loginpage);
        Name=(EditText)findViewById(R.id.etName);
        Password=(EditText)findViewById(R.id.etPassword);
        Login=(Button)findViewById(R.id.btnLogin);
        fAuth = FirebaseAuth.getInstance();
        Name = findViewById(R.id.etName);
        Password=findViewById(R.id.etPassword);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Name.getText().toString();
                String pwd = Password.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(HodLoginpage.this,"Fields are Empty!",Toast.LENGTH_SHORT).show();
                }
                else if(pwd.isEmpty())
                {
                    Toast.makeText(HodLoginpage.this,"Fields are Empty!",Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty() && pwd.isEmpty())
                {
                    Toast.makeText(HodLoginpage.this,"Fields are Empty!",Toast.LENGTH_SHORT).show();
                }
                fAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(HodLoginpage.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),hod.class));
                        }
                        else
                        {
                            Toast.makeText(HodLoginpage.this,"Login unsccessfull",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}