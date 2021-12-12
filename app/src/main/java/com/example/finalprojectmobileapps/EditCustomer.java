package com.example.finalprojectmobileapps;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class EditCustomer extends AppCompatActivity implements View.OnClickListener{
    Button btnSave;
    Button btnDelete;

    int updateID = -1;

    EditText txtName;
    EditText txtPhone;
    EditText txtAddress;
    RatingBar editRating;
    CalendarView editDate;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_customer);
        setTitle("Edit Customer");

        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        txtName = findViewById(R.id.txtEditName);
        txtPhone = findViewById(R.id.txtEditPhone);
        txtAddress = findViewById(R.id.txtEditAddress);
        editRating = findViewById(R.id.editRating);
        db = new DatabaseHelper(this);
        GlobalClass global = new GlobalClass();
        Customer customer = db.ViewOneCustomer(String.valueOf(global.customerID));
        updateID = customer.getId();
        txtName.setText(customer.getName());
        txtPhone.setText(customer.getPhone());
        txtAddress.setText(customer.getAddress());
        editRating.setRating(Float.parseFloat(String.valueOf(customer.getRating())));
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Intent a = new Intent(EditCustomer.this, ViewCustomers.class);
                startActivity(a);
            }
        };
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button btn = findViewById(v.getId());
        String submitName = txtName.getText().toString();
        String submitPhone = txtPhone.getText().toString();
        String submitAddress = txtAddress.getText().toString();
        String submitRating = String.valueOf(editRating.getRating());
        if(btn.getId() == btnSave.getId()){
            Customer updateCustomer = new Customer();
            if(submitName.isEmpty()){
                Toast.makeText(EditCustomer.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                return;
            }
            if(submitName.length() < 3 || submitName.length() > 50){
                Toast.makeText(EditCustomer.this, "Name must be between 3 and 50 characters", Toast.LENGTH_SHORT).show();
                return;
            }
            if(submitPhone.isEmpty()){
                Toast.makeText(EditCustomer.this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
                return;
            }
            if(submitAddress.isEmpty()){
                Toast.makeText(EditCustomer.this, "Please enter an address", Toast.LENGTH_SHORT).show();
                return;
            }
            if(submitAddress.length() < 10 || submitAddress.length() > 150){
                Toast.makeText(EditCustomer.this, "Address must be between 10 and 150 characters", Toast.LENGTH_SHORT).show();
                return;
            }
            updateCustomer.setId(updateID);
            updateCustomer.setName(submitName);
            updateCustomer.setPhone(submitPhone);
            updateCustomer.setAddress(submitAddress);
            db.updateCustomer(updateCustomer, submitRating);
            Toast.makeText(EditCustomer.this, "Customer has been updated", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(this, ViewCustomers.class);
            startActivity(a);
        }
        if(btn.getId() == btnDelete.getId()){
            db.deleteCustomer(updateID);
            Toast.makeText(EditCustomer.this, "Customer has been Deleted", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(this, ViewCustomers.class);
            startActivity(a);
        }
    }
}
