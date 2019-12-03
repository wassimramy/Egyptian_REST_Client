package com.wrkhalil.egyptian_rest_client;

public class Company {

    private String name;
    private String catchPhrase;
    private String bs;

    Company (String name, String catchPhrase, String bs){
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

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
