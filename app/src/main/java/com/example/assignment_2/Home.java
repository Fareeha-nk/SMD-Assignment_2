package com.example.assignment_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {

    Button btn_profile_picture, btn_personal_details, btn_summary_info, btn_education_info,btn_experience_info, btn_certifications_info, btn_references_info, btn_preview_info;

    private void init(){
        btn_profile_picture=findViewById(R.id.profile_picture);
        btn_personal_details=findViewById(R.id.personal_details);
        btn_summary_info=findViewById(R.id.summary_info);
        btn_education_info=findViewById(R.id.education_info);
        btn_experience_info=findViewById(R.id.experience_info);
        btn_certifications_info=findViewById(R.id.certifications_info);
        btn_references_info=findViewById(R.id.references_info);
        btn_preview_info=findViewById(R.id.preview_info);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        btn_profile_picture.setOnClickListener(v -> {
            Intent i_pp= new Intent(Home.this, ProfilePicture.class);
            startActivity(i_pp);
            finish();
        });

        btn_personal_details.setOnClickListener(v -> {
            Intent i_pd= new Intent(Home.this, PersonalDetails.class);
            startActivity(i_pd);
            finish();
        });

        btn_summary_info.setOnClickListener(v -> {
            Intent i_sum= new Intent(Home.this, Summary.class);
            startActivity(i_sum);
            finish();
        });

        btn_education_info.setOnClickListener(v -> {
            Intent i_edu= new Intent(Home.this, Education.class);
            startActivity(i_edu);
            finish();
        });

        btn_experience_info.setOnClickListener(v -> {
            Intent i_exp= new Intent(Home.this, Experience.class);
            startActivity(i_exp);
            finish();
        });

        btn_certifications_info.setOnClickListener(v -> {
            Intent i_cert= new Intent(Home.this, Certifications.class);
            startActivity(i_cert);
            finish();
        });

        btn_references_info.setOnClickListener(v -> {
            Intent i_ref= new Intent(Home.this, References.class);
            startActivity(i_ref);
            finish();
        });

        btn_preview_info.setOnClickListener(v -> {
            Intent i_preview= new Intent(Home.this, Preview.class);
            startActivity(i_preview);
            finish();
        });




    }
}