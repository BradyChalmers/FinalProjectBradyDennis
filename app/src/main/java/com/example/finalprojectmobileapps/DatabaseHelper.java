package com.example.finalprojectmobileapps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "FinalAssignmentDatabase";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Customers";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String PHONE_COL = "phone";
    private static final String ADDRESS_COL = "address";
    private static final String RATING_COL = "rating";
    private static final String DATE_COL = "date";

    public DatabaseHelper(Context context) {super(context, DB_NAME, null, DB_VERSION);}

    public void addNewCustomer(String name, String phone, String address, String rating, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, name);
        values.put(PHONE_COL, phone);
        values.put(ADDRESS_COL, address);
        values.put(RATING_COL, rating);
        values.put(DATE_COL, date);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public List<Customer> ViewCustomers(){
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String address = cursor.getString(3);
                String date = cursor.getString(5);
                int rating = cursor.getInt(4);

                Customer customer = new Customer(id, name, phone, address, rating, date);
                customers.add(customer);
            }while(cursor.moveToNext());
        }
        return customers;
    }

    public List<Customer> ViewSearchCustomers(String searchContent){
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE name LIKE '%" + searchContent + "%'";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String address = cursor.getString(3);
                String date = cursor.getString(5);
                int rating = cursor.getInt(4);

                Customer customer = new Customer(id, name, phone, address, rating, date);
                customers.add(customer);
            }while(cursor.moveToNext());
        }
        return customers;
    }

    public Customer ViewOneCustomer(String id){
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + id;
        SQLiteDatabase db = this.getWritableDatabase();

        Customer customer = new Customer();

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int getId = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String address = cursor.getString(3);
                String date = cursor.getString(5);
                int rating = cursor.getInt(4);

                customer = new Customer(getId, name, phone, address, rating, date);
            }while(cursor.moveToNext());
        }
        return customer;
    }

    public void updateCustomer(Customer customer, String rating){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        String whereClause = ID_COL + " = " + customer.getId();

        cv.put(NAME_COL, customer.getName());
        cv.put(PHONE_COL, customer.getPhone());
        cv.put(ADDRESS_COL, customer.getAddress());
        cv.put(RATING_COL, rating);

        db.update(TABLE_NAME, cv, whereClause, null);
    }

    public void deleteCustomer(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = " + id;
        db.execSQL(query);
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT, "
                + PHONE_COL + " TEXT, "
                + ADDRESS_COL + " TEXT, "
                + RATING_COL + " TEXT, "
                + DATE_COL + " TEXT)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}