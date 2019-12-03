package com.wrkhalil.egyptian_rest_client;

public class Geography {

    private double lat;
    private double lng;

    Geography (double lat, double lng){
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
