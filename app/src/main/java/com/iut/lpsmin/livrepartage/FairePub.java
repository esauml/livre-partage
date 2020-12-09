package com.iut.lpsmin.livrepartage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iut.lpsmin.livrepartage.model.Firebase;
import com.iut.lpsmin.livrepartage.model.Genre;
import com.iut.lpsmin.livrepartage.model.Livre;
import com.iut.lpsmin.livrepartage.model.Publication;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FairePub extends AppCompatActivity {
    public static final int CAMARA_REQUEST_CODE = 102;
    public static final int GALERIE_RECUEST = 105;
    public static final int CAMARA_REQUEST = 101;
    private Livre l;
    private Publication p;

    private TextInputLayout textTitre;
    private TextInputLayout textAuteur;
    private TextInputLayout textEdition;
    private TextInputLayout textVille;
    private Firebase mDatabaseRef;
    private Firebase mPubref;
    private TextView livreAff;
    private String user;
    String currentPhotoPath;
    Button cam,gal;

    String name;
    StorageReference storRef;
    Uri contentUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_faire_pub);
        mDatabaseRef=new Firebase("livres") ;
        mPubref=new Firebase("publications");
        textTitre=findViewById(R.id.textTitre);
        textAuteur=findViewById((R.id.textAuteur));
        textEdition=findViewById(R.id.textEdition);
        textVille=findViewById(R.id.textVille);
        livreAff=(TextView) findViewById(R.id.livreAff);
        cam=findViewById(R.id.btCamara);
        gal=findViewById(R.id.btGalerie);
    storRef= FirebaseStorage.getInstance().getReference();

        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCamaraPerm();
                Toast.makeText(FairePub.this,"Appareil photo",Toast.LENGTH_LONG).show();
            }
        });
        gal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FairePub.this,"Galerie",Toast.LENGTH_LONG).show();
                Intent galerie=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galerie, GALERIE_RECUEST);
            }
        });
    }

    private void askCamaraPerm() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMARA_REQUEST);
        }else{
            dispatchTakePictureIntent();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMARA_REQUEST_CODE) {
            Log.d("TAG","aaaaa"+currentPhotoPath);

               File photo = new File(currentPhotoPath);
                livreAff.setText("Image garde");
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(photo);
            mediaScanIntent.setData(contentUri);
            this.sendBroadcast(mediaScanIntent);
            name=photo.getName();
            this.contentUri=contentUri;

        }
        if (requestCode == GALERIE_RECUEST) {
            Uri contentUri=data.getData();
            String timeStamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName="JPEG_"+timeStamp+"."+getFileExt(contentUri);
            livreAff.setText("Image garde");
            name=imageFileName;
            this.contentUri=contentUri;

        }
    }

    private void uploadPhototoFF(String name, Uri contentUri) {
        StorageReference image =storRef.child("images/"+name);
        image.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FairePub.this,"Fail",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==CAMARA_REQUEST){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                dispatchTakePictureIntent();

            }else{
                Toast.makeText(this,"Camara permission required",Toast.LENGTH_SHORT).show();
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //File storageDir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    static final int REQUEST_IMAGE_CAPTURE=1;
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.iut.lpsmin.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMARA_REQUEST_CODE);
            }
        }
    }


    public void Enregistrer(View view) {

        StorageReference image =storRef.child("images/"+name);
        image.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
String uid=user.getUid();
                        l=new Livre(textTitre.getEditText().getText().toString(),
                                textAuteur.getEditText().getText().toString(),
                                textEdition.getEditText().getText().toString(),
                                new Genre(
                                        textVille.getEditText().getText().toString()),uri.toString(),uid);
                        p=new Publication(l);
                        mDatabaseRef.enRegist(l);
                        mPubref.enRegist(p);

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FairePub.this,"Fail",Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}