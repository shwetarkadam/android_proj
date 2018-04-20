package com.example.hp.mccfirebase;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.*;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import com.example.hp.mccfirebase.pojos.Upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Upload_Activity extends AppCompatActivity {


    private static final int PICK_IMAGE_REQUEST = 1;//identify image req


    private Button btnChooseImage;
    private Button btnUpload;
    private TextView textViewShowUploads;
    private EditText editTextFileName;
    private ImageView imageView;
    private ProgressBar progressBar;


    private Uri mImageUri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private StorageTask uploadtask;//created a variable to avoid multiple uploads
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_);


        btnChooseImage = findViewById(R.id.button_choose_image);
        btnUpload = findViewById(R.id.button_upload);
        textViewShowUploads = findViewById(R.id.text_view_show_uploads);
        editTextFileName = findViewById(R.id.edit_text_file_name);
        imageView = findViewById(R.id.image_view);
        progressBar = findViewById(R.id.progress_bar);

        storageReference= FirebaseStorage.getInstance().getReference("uploads");
        databaseReference=FirebaseDatabase.getInstance().getReference("uploads");

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openFileChooser();
            }
        });


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(uploadtask !=null && uploadtask.isInProgress()){
                Toast.makeText(Upload_Activity.this,"Upload is in Progress",Toast.LENGTH_SHORT).show();

            }else{
                upload();
            }



            }
        });


        textViewShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagesActivity();

            }
        });


    }

    private void openImagesActivity() {
    Intent i=new Intent(this,ShowImagesActivity.class);
    startActivity(i);


    }
    private void openFileChooser() {
        Intent i = new Intent();
        i.setType("image/*");//will see images of file choose chosen
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);

    }
    //will be called when we pick our file
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== PICK_IMAGE_REQUEST && resultCode== RESULT_OK && data !=null && data.getData() != null){
        mImageUri=data.getData();
        //image downloading and caching library for Android
            Picasso.with(this).load(mImageUri).into(imageView);

        }
    }

    //gets file extensions of images such as jpeg
    private String getFileExtension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void upload() {
        if(mImageUri != null){//actually selected a image
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            uploadtask=fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);//qhen delay happends set progress to 0
                        }
                    }, 500);
                    Toast.makeText(Upload_Activity.this,"Upload Sucessful",Toast.LENGTH_LONG);
                    Upload upload=new Upload(editTextFileName.getText().toString().trim(),taskSnapshot.getDownloadUrl().toString());
                    String uploadId = databaseReference.push().getKey();
                    databaseReference.child(uploadId).setValue(upload);//creates a entry in db with uploadid and also add meta dat
                }
            })
             .addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                     Toast.makeText(Upload_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                 }
             })
              .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                  @Override
                  public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                      double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                      progressBar.setProgress((int) progress);
                  }
              });

        }else{
           Toast.makeText(this,"No file Selected",Toast.LENGTH_SHORT).show();
        }
    }

}
