package com.example.mastro.selfit;

import java.util.List;

/**
 * Created by Mastro on 4/28/2017.
 */

public class Post {

    public String name;
    public String type;
    public String country;
    public String city;
    public String classType;
    public String urlPicture;
    public String location;
    public String templateID;
    public String weekID;
    public String  sportCenterID;
    public String postDescription;
    public List<String> arrayTemplatesID;
    public SportCenter sportCenter;

    public Post(){

    }

    public Post(String name, String type, String country, String city, String classType, String urlPicture, String location, String templateID, String weekID, String sportCenterID, String postDescription, List<String> arrayTemplatesID, SportCenter sportCenter) {
        this.name = name;
        this.type = type;
        this.country = country;
        this.city = city;
        this.classType = classType;
        this.urlPicture = urlPicture;
        this.location = location;
        this.templateID = templateID;
        this.weekID = weekID;
        this.sportCenterID = sportCenterID;
        this.postDescription = postDescription;
        this.arrayTemplatesID = arrayTemplatesID;
        this.sportCenter = sportCenter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTemplateID() {
        return templateID;
    }

    public void setTemplateID(String templateID) {
        this.templateID = templateID;
    }

    public String getWeekID() {
        return weekID;
    }

    public void setWeekID(String weekID) {
        this.weekID = weekID;
    }

    public String getSportCenterID() {
        return sportCenterID;
    }

    public void setSportCenterID(String sportCenterID) {
        this.sportCenterID = sportCenterID;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public List<String> getArrayTemplatesID() {
        return arrayTemplatesID;
    }

    public void setArrayTemplatesID(List<String> arrayTemplatesID) {
        this.arrayTemplatesID = arrayTemplatesID;
    }

    public SportCenter getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(SportCenter sportCenter) {
        this.sportCenter = sportCenter;
    }
}
