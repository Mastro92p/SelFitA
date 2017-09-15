package com.example.mastro.selfit;

/**
 * Created by Mastro on 1/18/2017.
 */


import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.List;


@IgnoreExtraProperties
public class User implements Serializable {

    public String name;
    public String birthday;
    public String country;
    public String countryID;
    public String userID;
    public String gender;
    public String email;
    public List<String> followingList;
    public List<String> sportCenterList;
    public List<String> classesList;
    public String profileImageUrl;


     public User(){

    }

    public User(String name, String birthday, String country, String countryID, String gender, String email) {
        this.name = name;
        this.birthday = birthday;
        this.country = country;
        this.countryID = countryID;
        this.gender = gender;
        this.email = email;
    }

    public User(String name, String birthday, String country, String pictureURL, String countryID, String gender, String email, List<String> followingList, List<String> sportCenterList, List<String> classesList) {

        this.name = name;
        this.birthday = birthday;
        this.country = country;
        this.countryID = countryID;
        this.gender = gender;
        this.email = email;
        this.classesList = classesList;
        this.followingList = followingList;
        this.sportCenterList = sportCenterList;
        this.profileImageUrl = pictureURL;

    }


    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryID() {
        return countryID;
    }

    public String getUserID() {
        return userID;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getFollowingList() {
        return followingList;
    }

    public void setFollowingList(List<String> followingList) {
        this.followingList = followingList;
    }

    public List<String> getSportCenterList() {
        return sportCenterList;
    }

    public void setSportCenterList(List<String> sportCenterList) {
        this.sportCenterList = sportCenterList;
    }

    public List<String> getClassesList() {
        return classesList;
    }

    public void setClassesList(List<String> classesList) {
        this.classesList = classesList;
    }

    public String getPictureURL() {
        return profileImageUrl;
    }

    public void setPictureURL(String pictureURL) {
        this.profileImageUrl = pictureURL;
    }
}
