package com.example.mastro.selfit;

import android.app.Activity;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.view.inputmethod.InputMethodManager;


import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;;

import android.support.annotation.NonNull;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.gson.Gson;

import java.util.Date;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private final int CodeActivity = 1;

    private DatabaseReference mDatabase;
    SharedPreferences mPrefs;

     EditText _firstnameText;
     EditText _emailText;EditText _passwordText;
     Button _signupButton;
     TextView _loginLink;
     ToggleButton _hombrebutton;
     ToggleButton _mujerbutton;
     TextView _date;
     LinearLayout selector;


    public String _sexo = null;
    public boolean _selected = false;
    boolean FlagDate = false;

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mPrefs = getPreferences(MODE_PRIVATE);

        _firstnameText = (EditText) findViewById(R.id.input_first_name);
        _emailText =(EditText) findViewById(R.id.input_email);
        _passwordText= (EditText) findViewById(R.id.input_password);
        _signupButton = (Button) findViewById(R.id.button);
        _loginLink = (TextView) findViewById(R.id.link_login);
        _hombrebutton = (ToggleButton)findViewById(R.id.hombre_button);
        _mujerbutton = (ToggleButton) findViewById(R.id.mujer_button) ;
        _date = (TextView) findViewById(R.id.birth);
        selector =(LinearLayout) findViewById(R.id.selector);


        mAuth = FirebaseAuth.getInstance();


        selector.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DatePickerFragment DatePicker = new DatePickerFragment();
                DatePicker.setActivityCode(CodeActivity);
                DatePicker.show(getFragmentManager(), "datePicker");

            }
        });


        _hombrebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _sexo = "Hombre";
                _selected =true;
                _mujerbutton.setChecked(false);
                _hombrebutton.setChecked(true);


            }
        });

        _mujerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _sexo = "Mujer";
                _selected =true;
                _mujerbutton.setChecked(true);
                _hombrebutton.setChecked(false);
            }
        });

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });

        _emailText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus  && !_firstnameText.hasFocus() && !_passwordText.hasFocus()) {
                    hideKeyboard(v);
                }
            }
        });


        _firstnameText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus  && !_emailText.hasFocus() && !_passwordText.hasFocus()) {
                    hideKeyboard(v);
                }
            }
        });

        _passwordText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus  && !_firstnameText.hasFocus() && !_emailText.hasFocus()) {
                    hideKeyboard(v);
                }
            }
        });

        _passwordText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            hideKeyboard(v);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                R.style.AppDialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _firstnameText.getText().toString();

        String date = _date.getText().toString();
        String sexo = _sexo;

        String country="Venezuela";
        String countryID="243";

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        final User user = new User(name,date,country,countryID,sexo,email);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();


                                            onSignupFailed();
                                            progressDialog.dismiss();


                        }else{

                            mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).setValue(user);

                                            onSignupSuccess(user);
                                            progressDialog.dismiss();

                        }


                    }
                });




    }


    public void onSignupSuccess(User user) {
        _signupButton.setEnabled(true);

        //SharedPreferences.Editor prefsEditor = mPrefs.edit();
        //Gson gson = new Gson();
        //String json = gson.toJson(user);
        //prefsEditor.putString("UserData", json);
        //prefsEditor.apply();

        setResult(RESULT_OK, null);
        finish();
    }



    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _firstnameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();


        if (name.isEmpty() || name.length() < 3) {
            _firstnameText.setError("at least 3 characters");
            valid = false;
        } else {
            _firstnameText.setError(null);
        }

        if(!_selected){

            valid = false;

        }

        if(!FlagDate){

            valid = false;
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    public void updateDateFlag(){

        FlagDate = true;
    }

}
