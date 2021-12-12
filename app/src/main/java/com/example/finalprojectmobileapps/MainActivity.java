package com.example.finalprojectmobileapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView countDisplay;
    Button addNewCustomer;
    Button viewCustomers;
    Button searchCustomers;
    Button exitGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Menu");

        addNewCustomer = findViewById(R.id.btnAddNew);
        viewCustomers =  findViewById(R.id.btnViewCustomers);


        addNewCustomer.setOnClickListener(this);
        viewCustomers.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button btn = findViewById(v.getId());
        if(btn.getId() == addNewCustomer.getId()){
            Intent a = new Intent(this, AddNewCustomer.class);
            startActivity(a);
        }
        if(btn.getId() == viewCustomers.getId()){
            Intent a = new Intent(this, ViewCustomers.class);
            startActivity(a);
        }
    }
}