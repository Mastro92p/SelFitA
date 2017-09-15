package com.example.mastro.selfit;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    int actcode = 0;

    public void setActivityCode(int code){

        actcode = code;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog DateDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        DateDialog.getDatePicker().setMaxDate(new Date().getTime());

        return DateDialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        String selectedDate;
        TextView birth = (TextView)getActivity().findViewById(R.id.birth);

        DateFormater format = new DateFormater();
        selectedDate = format.formatDate(year,month,day);
        birth.setText(selectedDate);

        if(actcode==1){

            SignUpActivity activity = (SignUpActivity) getActivity();
            activity.updateDateFlag();

        }


        this.dismiss();

    }



}
