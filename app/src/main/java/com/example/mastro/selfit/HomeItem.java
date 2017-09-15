package com.example.mastro.selfit;

/**
 * Created by Mastro on 3/10/2017.
 */

public class HomeItem extends Item{

    private String title;
    private String location = "Caracas, Venezuela";
    private String thumbnail;
    private String background;
    private String body =  "Place holder, Info about item is targeted here";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
