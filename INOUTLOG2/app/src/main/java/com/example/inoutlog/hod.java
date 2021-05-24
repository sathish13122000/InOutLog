package com.example.inoutlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class hod extends AppCompatActivity {

    TextView name, reason, leaving, returning;
    Button display,allowed,rejected;
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    ApprovalInfo approvalInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod);

        name = findViewById(R.id.tv7);
        reason = findViewById(R.id.tv9);
        leaving = findViewById(R.id.tv13);
        returning = findViewById(R.id.tv14);
        allowed = findViewById(R.id.button1);
        rejected =findViewById(R.id.button2);
        display = findViewById(R.id.button3);
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.


        approvalInfo = new ApprovalInfo();


        allowed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = firebaseDatabase.getReference("ApprovalInfo");
                String allow="Permission Granted";
                addDatatoFirebase(allow);
            }
        });

        rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = firebaseDatabase.getReference("ApprovalInfo");
                String reject="Permission Denied";
                addDatatoFirebase(reject);
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = firebaseDatabase.getInstance().getReference().child("StaffInfo");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String staffName = dataSnapshot.child("staffName").getValue().toString();
                        String staffreason = dataSnapshot.child("staffreason").getValue().toString();
                        String staffleaving = dataSnapshot.child("staffleaving").getValue().toString();
                        String staffreturning = dataSnapshot.child("staffreturning").getValue().toString();

                        name.setText(staffName);
                        reason.setText(staffreason);
                        leaving.setText(staffleaving);
                        returning.setText(staffreturning);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
    private void addDatatoFirebase(String status ) {
        // below 3 lines of code is used to set
        // data in our object class.
        approvalInfo.setStatus(status);


        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(approvalInfo);

                // after adding this data we are showing toast message.
                Toast.makeText(hod.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(hod.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

