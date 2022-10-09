package com.example.picclick;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final int Camera_request = 101;
    public static final int Open_cam_req = 102;
    String currentPhotoPath;
    Button click, download;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click = findViewById(R.id.click);
        download = findViewById(R.id.download);
        img = findViewById(R.id.img);
        // Disable state for downloading image before clicking photo, to prevent
        download.setEnabled(false);
        download.setVisibility(View.INVISIBLE);
        click.setOnClickListener(new View.OnClickListener() {
            // checking for minimum version required, N = Nougat
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                //Asking camera permissions
                //Manifest.permission.CAMERA gets us the current situation for permissions and we match that with PackageManager.PERMISSION_GRANTED, which know is code for granted permission
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                    // We request permissions when our permission was not already granted. we are requesting permission within MainActivity as context, and every request needs i's own unique code, which here is Camera_request
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, Camera_request);
                }
                else{
                    //if permission are given then call picture taking function
                    dispatchTakePictureIntent();
                }
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                getImageUri(MainActivity.this,((BitmapDrawable)img.getDrawable()).getBitmap());
                Toast.makeText(MainActivity.this, "Image Downloaded.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Handles the results for our permission requests
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == Camera_request){
            // requestCode== camera_request means we are camera permissions
            if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                // If permission was granted then we start our camera taking function
                dispatchTakePictureIntent();
            }
            else{
                //permission not given
                Toast.makeText(this, "Camera permission required to use camera.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //gets our result back from camera app and set that file we got to image view.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Open_cam_req){
            if(resultCode == Activity.RESULT_OK){
                File f = new File(currentPhotoPath);
                img.setImageURI(Uri.fromFile(f));
                // set the visibility and clickable button to true after  user has clicked the image to that he can download the image
                download.setVisibility(View.VISIBLE);
                download.setEnabled(true);
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.FRENCH).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                //authority i.e. provider name could be anything
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.PicClick_https://github.com/jmanan088",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, Open_cam_req);
            }
        }
    }

    //Function to save image to external storage
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        //Storing the photo with date-time format as name
        String timeStamp = new SimpleDateFormat("dd_MM_yyyy-HH.mm.ss", Locale.FRENCH).format(new Date());
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, timeStamp, null);
    }
}