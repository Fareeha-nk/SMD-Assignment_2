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
    Button btn_save_certification, btn_cancel_certification;


    private void init(){
        et_Certifications_Card=findViewById(R.id.certification_card);
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


        btn_save_certification.setOnClickListener(v -> {
            String Certificates= et_Certifications_Card.getText().toString().trim();
            if(Certificates.isEmpty()){
                Toast.makeText(this, "Please fill the field!", Toast.LENGTH_SHORT).show();
            }
            else if(Certificates.length()<5 || Certificates.length()>200){
                Toast.makeText(this, "Your word count is not appropriate", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent1 = new Intent(Certifications.this, Home.class);
                Toast.makeText(this, "Certificate information saved successfully!", Toast.LENGTH_SHORT).show();
                intent1.putExtra("Certificates", Certificates);
                setResult(RESULT_OK, intent1);
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