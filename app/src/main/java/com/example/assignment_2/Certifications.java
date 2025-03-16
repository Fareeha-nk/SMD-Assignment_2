package com.example.assignment_2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Certifications extends AppCompatActivity {
    EditText et_Certifications_Card;
    ImageView certification_camera;
    Button btn_save_certification, btn_cancel_certification;
    private Uri selectedImage;

    private void init(){
        et_Certifications_Card=findViewById(R.id.certification_card);
        certification_camera=findViewById(R.id.camera_logo_cert);
        btn_save_certification=findViewById(R.id.save_button_certifications);
        btn_cancel_certification=findViewById(R.id.cancel_button_certifications);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_certifications);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        String ImageURI=getIntent().getStringExtra("savedImageUri");
        if(ImageURI!=null){
            selectedImage=Uri.parse(ImageURI);
            certification_camera.setImageURI(selectedImage);
        }

        ActivityResultLauncher<Intent> intent;
        intent=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result -> {
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                selectedImage=result.getData().getData();
                certification_camera.setImageURI(selectedImage);
            }
            else{
                Toast.makeText(this, "No Image selected", Toast.LENGTH_SHORT).show();
            }
        }));

        certification_camera.setOnClickListener(v -> {
            Intent uploadIntent= new Intent(Intent.ACTION_PICK);
            uploadIntent.setType("image/*");
            intent.launch(uploadIntent);
        });

        btn_save_certification.setOnClickListener(v -> {
            String Certificates= et_Certifications_Card.getText().toString().trim();
            if(Certificates.isEmpty()){
                Toast.makeText(this, "Please fill the field!", Toast.LENGTH_SHORT).show();
            }
            else if(Certificates.length()<5 || Certificates.length()>70){
                Toast.makeText(this, "Your word count is not appropriate", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent1 = new Intent(Certifications.this, Home.class);
                if (selectedImage != null) {
                    intent1.putExtra("savedImageUri", selectedImage.toString());
                }
                Toast.makeText(this, "Certificate information saved successfully!", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                finish();
            }
        });

        btn_cancel_certification.setOnClickListener(v -> {
            Toast.makeText(this, "Changes Discarded", Toast.LENGTH_SHORT).show();
            Intent intent1= new Intent(Certifications.this, Home.class);
            startActivity(intent1);
            finish();
        });

    }
}