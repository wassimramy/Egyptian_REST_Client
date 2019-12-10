package com.wrkhalil.egyptian_rest_client;

public class User {

    //Attributes
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    //User Constructor
    User (int id, String name, String username, String email, Address address,
          String phone, String website, Company company){
        this.id = id; //Fetch id
        this.name = name; //Fetch name
        this.username = username; //Fetch username
        this.email = email; //Fetch email
        this.address = address; //Fetch address
        this.phone = phone; //Fetch phone
        this.website = website; //Fetch website
        this.company = company; //Fetch company
    }

    //Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public Address getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String getWebsite() {
        return website;
    }
    public Company getCompany() {
        return company;
    }
    public String getUsername() {
        return username;
    }
}
