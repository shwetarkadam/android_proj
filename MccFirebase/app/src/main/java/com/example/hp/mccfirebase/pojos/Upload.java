package com.example.hp.mccfirebase.pojos;

/**
 * Created by HP on 24-02-2018.
 */

public class Upload {

    private String name;
    private String imageUrl;



    public Upload() {

    }

    public Upload(String name, String imageUrl) {
        if(name.trim().equals("")){
            name="No name";     //if useer doesnt mentions a name or puts blank spaces(hence trim is used)
        }

        this.name = name;
        this.imageUrl=imageUrl;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
