package com.example.finalprojectmobileapps;

public class Customer {
    private int id;
    private int rating;
    private String name;
    private String phone;
    private String address;
    private String date;

    public Customer() {

    }

    public Customer(int id, String name, String phone, String address, int rating, String date)
    {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.rating = rating;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name){ this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone){ this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address){ this.address = address; }
    public int getRating() { return rating; }
    public void setRating(int rating){ this.rating = rating; }
    public String getDate() { return date; }
    public void setDate(String date){ this.date = date; }

    @Override
    public String toString(){
        return "Customer ID: " + id + "\n" + "Name: " + name + "\n" + "Phone: " + phone + "\n" + "Address: " + address + "\n" + "Rating: " + rating + "/5\n" + "Date: " + date;
    }

}
