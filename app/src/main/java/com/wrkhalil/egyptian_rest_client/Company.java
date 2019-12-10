package com.wrkhalil.egyptian_rest_client;

public class Company {

    //Attributes
    private String name;
    private String catchPhrase;
    private String bs;

    //Company Constructor
    Company (String name, String catchPhrase, String bs){
        this.name = name; //Fetch name
        this.catchPhrase = catchPhrase; //Fetch catchPhrase
        this.bs = bs; //Fetch bs
    }

    //Getters
    public String getName() {
        return name;
    }
    public String getCatchPhrase() {
        return catchPhrase;
    }
    public String getBs() {
        return bs;
    }

}
