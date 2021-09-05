package com.example.dmce2;

public class uploadPdf {
    String url,name;

    public uploadPdf( String name,String url) {

        this.name = name;
        this.url = url;
    }

    public uploadPdf() {
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
