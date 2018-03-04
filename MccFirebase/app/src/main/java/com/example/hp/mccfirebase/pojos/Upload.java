package com.example.hp.mccfirebase.pojos;

/**
 * Created by HP on 24-02-2018.
 */

public class Upload {

    private String name;
    private String imageUrl;
    private int images;
    private String title;
    private String desc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

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
