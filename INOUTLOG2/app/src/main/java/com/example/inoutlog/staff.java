package com.example.inoutlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class staff extends AppCompatActivity {

    private EditText name,reason,leaving,returning,permission;
    Button submit,check;
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
     StaffInfo staffInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        // initializing our edittext and button
        name = findViewById(R.id.editTextTextPersonName2);
        reason = findViewById(R.id.editTextTextPersonName3);
        leaving = findViewById(R.id.editTextTime);
        returning=findViewById(R.id.editTextTime2);
        permission=findViewById(R.id.editTextTextPersonName);


        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("StaffInfo");

        // initializing our object
        // class variable.
        staffInfo = new StaffInfo();

        submit = findViewById(R.id.button);

        check=findViewById(R.id.button4);

        // adding on click listener for our button.
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String sname = name.getText().toString();
                String sreason = reason.getText().toString();
                String sleaving = leaving.getText().toString();
                String sreturning = returning.getText().toString();

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(sname) && TextUtils.isEmpty(sreason) && TextUtils.isEmpty(sleaving) && TextUtils.isEmpty(sreturning)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(staff.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(sname, sreason, sleaving,sreturning);
                }
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = firebaseDatabase.getInstance().getReference().child("ApprovalInfo");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String stat=snapshot.child("status").getValue().toString();

                        permission.setText(stat);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private void addDatatoFirebase(String name, String reason, String leaving,String returning) {
        // below 3 lines of code is used to set
        // data in our object class.
        staffInfo.setStaffName(name);
        staffInfo.setStaffreason(reason);
        staffInfo.setStaffleaving(leaving);
        staffInfo.setStaffreturning(returning);


        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(staffInfo);

                // after adding this data we are showing toast message.
                Toast.makeText(staff.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(staff.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}