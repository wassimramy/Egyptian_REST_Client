package com.wrkhalil.egyptian_rest_client;

public class Address {

    //Attributes
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geography geo;

    //Address Constructor
    Address (String street, String suite, String city, String zipcode, Geography geo){
        this.street = street; //Fetch street
        this.suite = suite; //Fetch suite
        this.city = city; //Fetch city
        this.zipcode = zipcode; //Fetch zipcode
        this.geo = geo; //Fetch geo
    }

    //Getters
    public String getStreet() {
        return street;
    }
    public String getSuite() {
        return suite;
    }
    public String getCity() {
        return city;
    }
    public String getZipcode() {
        return zipcode;
    }
    public Geography getGeo() {
        return geo;
    }
}
