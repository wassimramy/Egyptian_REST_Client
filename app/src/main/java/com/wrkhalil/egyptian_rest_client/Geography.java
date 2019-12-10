package com.wrkhalil.egyptian_rest_client;

public class Geography {

    //Attributes
    private double lat;
    private double lng;

    //Geography constructor
    Geography (double lat, double lng){
        this.lat = lat; //Fetch lat
        this.lng = lng; //Fetch long
    }

    //Getters
    public double getLat() {
        return lat;
    }
    public double getLng() {
        return lng;
    }
}
