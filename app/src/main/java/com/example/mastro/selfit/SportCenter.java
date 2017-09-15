package com.example.mastro.selfit;

import java.util.List;

/**
 * Created by Mastro on 4/28/2017.
 */

public class SportCenter {

    public String name;
    public String birthday;
    public String country;
    public String city;
    public String centerID;
    public String adress;
    public String email;
    public  boolean follow;
    public List<String> followersList;
    public List<String> clientList;

    public SportCenter() {

    }

    public SportCenter(String name, String birthday, String country, String city, String centerID, String adress, String email, boolean follow, List<String> followersList, List<String> clientList) {
        this.name = name;
        this.birthday = birthday;
        this.country = country;
        this.city = city;
        this.centerID = centerID;
        this.adress = adress;
        this.email = email;
        this.follow = follow;
        this.followersList = followersList;
        this.clientList = clientList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getCenterID() {
        return centerID;
    }

    public void setCenterID(String centerID) {
        this.centerID = centerID;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }

    public List<String> getFollowersList() {
        return followersList;
    }

    public void setFollowersList(List<String> followersList) {
        this.followersList = followersList;
    }

    public List<String> getClientList() {
        return clientList;
    }

    public void setClientList(List<String> clientList) {
        this.clientList = clientList;
    }
}
