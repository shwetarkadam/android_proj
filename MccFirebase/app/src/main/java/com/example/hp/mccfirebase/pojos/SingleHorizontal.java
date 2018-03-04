package com.example.hp.mccfirebase.pojos;

/**
 * Created by HP on 24-02-2018.
 */

public class SingleHorizontal {




    private int images;
    private String title;
    private String desc;
    public SingleHorizontal() {

    }

    public SingleHorizontal(int images, String title, String desc, String pubDate) {
        this.images = images;
        this.title = title;
        this.desc = desc;
        //this.pubDate = pubDate;
    }
    public String getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;

    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }
}
