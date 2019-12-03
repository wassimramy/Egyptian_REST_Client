package com.wrkhalil.egyptian_rest_client;

public class Address {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geography geo;

    Address (String street, String suite, String city, String zipcode, Geography geo){
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

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
