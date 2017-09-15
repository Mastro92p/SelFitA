package com.example.mastro.selfit;

/**
 * Created by Mastro on 4/28/2017.
 */

public class Subscriber {

    public String endOfContract;
    public String estate;
    public String numberOfActualClasses;
    public String numberOfClasses;
    public String price;

    public Subscriber(){


    }

    public Subscriber(String endOfContract, String estate, String numberOfActualClasses, String numberOfClasses, String price) {
        this.endOfContract = endOfContract;
        this.estate = estate;
        this.numberOfActualClasses = numberOfActualClasses;
        this.numberOfClasses = numberOfClasses;
        this.price = price;
    }

    public String getEndOfContract() {
        return endOfContract;
    }

    public void setEndOfContract(String endOfContract) {
        this.endOfContract = endOfContract;
    }

    public String getEstate() {
        return estate;
    }

    public void setEstate(String estate) {
        this.estate = estate;
    }

    public String getNumberOfActualClasses() {
        return numberOfActualClasses;
    }

    public void setNumberOfActualClasses(String numberOfActualClasses) {
        this.numberOfActualClasses = numberOfActualClasses;
    }

    public String getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(String numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
