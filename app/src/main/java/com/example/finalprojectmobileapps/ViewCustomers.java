package com.example.finalprojectmobileapps;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewCustomers extends AppCompatActivity{
    ListView lv;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_customers);
        setTitle("View Customers");

        lv = findViewById(R.id.lvCustomers);
        db = new DatabaseHelper(this);
        List<Customer> customers = db.ViewCustomers();

        ArrayAdapter adapter = new ArrayAdapter<Customer>(ViewCustomers.this, android.R.layout.simple_list_item_1, customers);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer customer = (Customer) parent.getItemAtPosition(position);
                GlobalClass global = (GlobalClass) getApplicationContext();
                global.customerID = customer.getId();
                if(global.customerID != -1)
                {
                    Intent a = new Intent(ViewCustomers.this, EditCustomer.class);
                    startActivity(a);
                }
            }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Intent a = new Intent(ViewCustomers.this, MainActivity.class);
                startActivity(a);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search for customer name :)");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                lv = findViewById(R.id.lvCustomers);
                db = new DatabaseHelper(ViewCustomers.this);
                List<Customer> customers = db.ViewSearchCustomers(newText);

                ArrayAdapter adapter = new ArrayAdapter<Customer>(ViewCustomers.this, android.R.layout.simple_list_item_1, customers);

                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Customer customer = (Customer) parent.getItemAtPosition(position);
                        GlobalClass global = (GlobalClass) getApplicationContext();
                        global.customerID = customer.getId();
                        if(global.customerID != -1)
                        {
                            Intent a = new Intent(ViewCustomers.this, EditCustomer.class);
                            startActivity(a);
                        }
                    }
                });
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
