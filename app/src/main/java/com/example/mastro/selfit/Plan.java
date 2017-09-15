package com.example.mastro.selfit;

/**
 * Created by Mastro on 4/28/2017.
 */

public class Plan {

    public String name;
    public String price;
    public String planDescription;

    public Plan(){


    }

    public Plan(String name, String price, String planDescription) {
        this.name = name;
        this.price = price;
        this.planDescription = planDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPlanDescription() {
        return planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }
}
