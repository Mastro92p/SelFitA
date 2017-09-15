package com.example.mastro.selfit;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {

    User user;
    String UserID;
    private FirebaseAuth auth;
    EditText name;
    TextView email;
    TextView birth;
    TextView country;
    ImageView imageView;
    boolean ProfileEdited = false;
    String mCurrentPhotoPath;
    Uri mUri;

    private StorageReference mStorage;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int SELECT_FILE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(R.string.EditProfile);
        }

        mStorage = FirebaseStorage.getInstance().getReference();

        user = (User)getIntent().getSerializableExtra("user");
        UserID = getIntent().getStringExtra("userID");

        name = (EditText)findViewById(R.id.fullname);
        email = (TextView)findViewById(R.id.email);
        birth = (TextView)findViewById(R.id.birth);
        country = (TextView)findViewById(R.id.country);

        TextView reset = (TextView)findViewById(R.id.resetPassword);

        name.setText(user.getName());
        email.setText(user.getEmail());
        birth.setText(user.getBirthday());
        country.setText(user.getCountry());

        auth = FirebaseAuth.getInstance();

        imageView = (ImageView) findViewById(R.id.profileImage);

        if(user.getPictureURL() == null) {

            Glide.with(this).load(R.drawable.profile).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .placeholder(R.drawable.profile)
                    .error(R.drawable.profile)
                    .into(new BitmapImageViewTarget(imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            imageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });

        }else{

            StorageReference storage = FirebaseStorage.getInstance().getReference().child("userProfile").child(user.userID).child("profilePicture.jpg");
            Glide.with(this)
                    .using(new FirebaseImageLoader())
                    .load(storage).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).centerCrop().placeholder(R.drawable.profile).error(R.drawable.profile)
                    .into(new BitmapImageViewTarget(imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            imageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });

        }


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showImageOption(v);

            }
        });

        TextWatcher tw = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ProfileEdited = true;

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        int maxLength = 21;
        name.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});

        birth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DatePickerFragment DatePicker = new DatePickerFragment();
                DatePicker.show(getFragmentManager(), "datePicker");


            }
        });

        birth.addTextChangedListener(tw);
        name.addTextChangedListener(tw);

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    if(!validate()){

                        hideKeyboard(v);
                        return;

                    }

                        name.clearFocus();
                        hideKeyboard(v);

                }
            }
        });

        name.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:

                            if(!validate()){

                                return false;

                            }else {

                                name.clearFocus();
                                hideKeyboard(v);
                                return true;
                            }

                        default:
                            break;
                    }
                }

                return false;
            }
        });

        //Toast.makeText(getApplication(), user.getUserID() ,Toast.LENGTH_SHORT).show();


        reset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                sendResetEmail(v);

            }

        });

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public boolean validate() {

        boolean valid = true;

        String email = name.getText().toString();

        if (email.isEmpty()) {
            name.setError("Campo vacio");
            valid = false;
        } else {
            name.setError(null);
        }

        return valid;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profilemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){

            if(!this.ProfileEdited) {

                onBackPressed();

            }else{


                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(findViewById(R.id.activity_profile).getContext(), R.style.AppDialog));
                builder.setTitle(R.string.EditProfile).setMessage(R.string.EditPMessage);


                builder.setPositiveButton(R.string.Accept, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button

                        Toast.makeText(getApplication(), "Done", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        setResult(RESULT_CANCELED, null);
                        onBackPressed();

                    }
                });

                builder.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        System.out.println("Accion cancelada");
                        dialog.dismiss();

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }


        }else if(id == R.id.Done){


            if(!this.ProfileEdited) {

                onBackPressed();

            }else{

                UpdateUser();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = null;

        if(resultCode == RESULT_OK) {

            this.ProfileEdited = true;

            if (requestCode == REQUEST_IMAGE_CAPTURE) {

                //Toast.makeText(getApplication(), data.getData().getPath() ,Toast.LENGTH_SHORT).show();
                uri = Uri.fromFile(new File(mCurrentPhotoPath));

                Glide.with(this).load(uri)
                        .asBitmap()
                        .centerCrop()
                        .placeholder(R.drawable.profile)
                        .error(R.drawable.profile)
                        .into(new BitmapImageViewTarget(imageView) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                imageView.setImageDrawable(circularBitmapDrawable);
                            }
                        });

            } else if (requestCode == SELECT_FILE) {

                uri = data.getData();

                Glide.with(this).load(uri)
                        .asBitmap()
                        .centerCrop()
                        .placeholder(R.drawable.profile)
                        .error(R.drawable.profile)
                        .into(new BitmapImageViewTarget(imageView) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                imageView.setImageDrawable(circularBitmapDrawable);
                            }
                        });


            } else {

                Toast.makeText(getApplication(), "Unable to load Image", Toast.LENGTH_SHORT).show();

                Glide.with(this).load(R.drawable.profile)
                        .asBitmap()
                        .centerCrop()
                        .placeholder(R.drawable.profile)
                        .error(R.drawable.profile)
                        .into(new BitmapImageViewTarget(imageView) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                imageView.setImageDrawable(circularBitmapDrawable);
                            }
                        });
            }

            mUri = uri;

        }
    }

    protected void UpdateUser(){

        final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();


        user.setBirthday(birth.getText().toString());
        user.setName(name.getText().toString());

        final Intent intent = new Intent();
        intent.putExtra("user",user);

        mDatabase.getReference().child("users").child(UserID).child("name").setValue(user.name);
        mDatabase.getReference().child("users").child(UserID).child("birthday").setValue(user.birthday);


        if(mUri!=null) {

            StorageReference storage = FirebaseStorage.getInstance().getReference().child("userProfile").child(UserID).child("profilePicture.jpg");

            storage.putFile(mUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(ProfileActivity.this, "Image Uploaded", Toast.LENGTH_SHORT).show();

                }
            });

            storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    user.setPictureURL(uri.toString());
                    mDatabase.getReference().child("users").child(UserID).child("profileImageUrl").setValue(user.profileImageUrl);

                    intent.setData(mUri);
                    setResult(RESULT_OK, intent);

                    finish();

                }
            });


        }


    }

    public void startGallery(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        if(intent.resolveActivity(getPackageManager())!=null) {

            startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);

        }


    }

    public void startCamera(){

        //Toast.makeText(getApplication(), "CAMERA",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager())!=null) {

            File file = null;

            try {

                file = createImageFile();


            } catch (Exception e) {

                e.printStackTrace();
            }

            if(file!=null){

                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }

        }


    }

    public void sendResetEmail(View v){

        final String email = user.getEmail();

        if(TextUtils.isEmpty(email)){

            Toast.makeText(getApplication(), "No Email found",Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(v.getContext(), R.style.AppDialog));
        builder.setTitle(R.string.ResetDialog) .setMessage(R.string.ResetMessage);


        builder.setPositiveButton(R.string.Send, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button

                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ProfileActivity.this, "Correo para Restablecer enviado", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ProfileActivity.this, "Error enviando correo", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                System.out.println("Accion cancelada");
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void showImageOption(View v){

        List<String> mOptions = new ArrayList<String>();
        mOptions.add("Camera");
        mOptions.add("Gallery");

        final CharSequence[] Options = mOptions.toArray(new String[mOptions.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        builder.setItems(Options, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which){

                if(which == 0){

                    startCamera();
                    dialog.dismiss();


                }else if(which == 1){

                    startGallery();
                    dialog.dismiss();

                }

            }

        });


        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

}
