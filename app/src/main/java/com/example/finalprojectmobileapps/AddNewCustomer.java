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

public class AddNewCustomer extends AppCompatActivity implements View.OnClickListener{
    Button btnSubmit;
    EditText name;
    EditText phone;
    EditText address;
    RatingBar rating;
    CalendarView date;

    String addDate;

    private DatabaseHelper db;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_customer);
        setTitle("Add New Customer");

        db = new DatabaseHelper(AddNewCustomer.this);

        btnSubmit = findViewById(R.id.btnSubmit);
        name = findViewById(R.id.txtName);
        phone = findViewById(R.id.txtPhone);
        address = findViewById(R.id.txtAddress);
        rating = findViewById(R.id.customerRating);
        date = findViewById(R.id.dateOfMeal);
        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                //Toast.makeText(MainActivity.this, month + 1 + "/" + day + "/" + year, Toast.LENGTH_LONG).show();
                String updateDate = month + 1 + "/" + day + "/" + year;
                addDate = updateDate;
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Intent a = new Intent(AddNewCustomer.this, MainActivity.class);
                startActivity(a);
            }
        };
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String submitName = name.getText().toString();
        String submitPhone = phone.getText().toString();
        String submitAddress = address.getText().toString();
        String submitRating = String.valueOf(rating.getRating());
        String submitDate = addDate;

        if(submitName.isEmpty()){
            Toast.makeText(AddNewCustomer.this, "Please enter a name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(submitName.length() < 3 || submitName.length() > 50){
            Toast.makeText(AddNewCustomer.this, "Name must be between 3 and 50 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        if(submitPhone.isEmpty()){
            Toast.makeText(AddNewCustomer.this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
            return;
        }
        if(submitAddress.isEmpty()){
            Toast.makeText(AddNewCustomer.this, "Please enter an address", Toast.LENGTH_SHORT).show();
            return;
        }
        if(submitAddress.length() < 10 || submitAddress.length() > 150){
            Toast.makeText(AddNewCustomer.this, "Address must be between 10 and 150 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        if(submitDate.isEmpty()){
            Toast.makeText(AddNewCustomer.this, "Please provide the date of your arrival", Toast.LENGTH_SHORT).show();
            return;
        }

        db.addNewCustomer(submitName, submitPhone, submitAddress, submitRating, submitDate);
        Toast.makeText(AddNewCustomer.this, "New customer has been added!", Toast.LENGTH_SHORT).show();
        name.setText("");
        phone.setText("");
        address.setText("");
        rating.setRating(0);
    }
}
