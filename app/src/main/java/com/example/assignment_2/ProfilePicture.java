package com.example.assignment_2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfilePicture extends AppCompatActivity {

    ImageView iv_person_logo, iv_camera_logo;
    Button btn_save, btn_cancel;
    private Uri selectedImageUri;

    private void init(){
        iv_person_logo=findViewById(R.id.person_logo);
        iv_camera_logo=findViewById(R.id.camera_logo);
        btn_save=findViewById(R.id.save_button);
        btn_cancel=findViewById(R.id.cancel_button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_picture);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        String imageUriString = getIntent().getStringExtra("savedImageUri");
        if (imageUriString != null) {
            selectedImageUri = Uri.parse(imageUriString);
            iv_person_logo.setImageURI(selectedImageUri);
        }

        ActivityResultLauncher<Intent> intent;
        intent=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                selectedImageUri = result.getData().getData();
                iv_person_logo.setImageURI(selectedImageUri);
            }
            else {
                Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
            }
        }));

        iv_camera_logo.setOnClickListener(v -> {
            Intent uploadIntent= new Intent(Intent.ACTION_PICK);
            uploadIntent.setType("image/*");
            intent.launch(uploadIntent);
        });

        btn_save.setOnClickListener(v -> {
            if (selectedImageUri != null) {
                Intent intent1 = new Intent(ProfilePicture.this, Home.class);
                intent1.putExtra("savedImageUri", selectedImageUri.toString()); // Pass image URI
                Toast.makeText(this, "Profile Picture Updated Successfully!", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                finish();
            } else {
                Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show();
            }
        });

        btn_cancel.setOnClickListener(v -> {
            Toast.makeText(this, "Changes Discarded!", Toast.LENGTH_SHORT).show();
            navigateToHome();
        });
    }

    private void navigateToHome() {
        Intent intent = new Intent(ProfilePicture.this, Home.class);
        startActivity(intent);
        finish();
    }
}