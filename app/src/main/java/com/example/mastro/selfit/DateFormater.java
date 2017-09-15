package com.example.mastro.selfit;

/**
 * Created by Mastro on 2/2/2017.
 */

public class DateFormater {

    String Date;

    public String getFormatedDate(){

        return Date;
    }

    public String formatDate(int year, int month, int day){

        month++;
        String date;


        if(month < 10 && day >= 10) {

            date = String.valueOf(day) + "/0" + String.valueOf(month) + "/" + String.valueOf(year);

        }else if(month < 10 && day < 10){

            date = "0" +String.valueOf(day) + "/0" + String.valueOf(month) + "/" + String.valueOf(year);

        }else if(month >= 10 && day < 10){

            date = "0" +String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);

        }else{

            date = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);

        }

        Date = date;
        return date;
    }

}
