package com.example.assignment_2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.net.URI;

public class Home extends AppCompatActivity {

    Button btn_profile_picture, btn_personal_details, btn_summary_info, btn_education_info,btn_experience_info, btn_certifications_info, btn_references_info, btn_preview_info;
    private Uri selectedImageUri;
    ActivityResultLauncher<Intent> profile_picture_intent;
    ActivityResultLauncher<Intent> personal_details;
    ActivityResultLauncher<Intent> summary;
    ActivityResultLauncher<Intent> education;
    ActivityResultLauncher<Intent> work_experience;
    ActivityResultLauncher<Intent> certifications;
    ActivityResultLauncher<Intent> references;
    private String profile_picture_uri;

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

        Intent intent1 = new Intent(Home.this, Preview.class);

        //from Home to respective activities and getting data from activities to Home (Profile Picture)
        btn_profile_picture.setOnClickListener(v -> {
            Intent uploadIntent= new Intent(Intent.ACTION_PICK);
            uploadIntent.setType("image/*");
            profile_picture_intent.launch(uploadIntent);
        });

        //getting data from activity to Home (Profile Picture)
        profile_picture_intent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result) -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Uri Image= result.getData().getData();
                Toast.makeText(this, "Profile picture uploaded successfully!", Toast.LENGTH_SHORT).show();
                intent1.putExtra("picture", Image.toString());
            }
        });

        //(Personal Detail)
        btn_personal_details.setOnClickListener(v -> {
            Intent i_pd= new Intent(Home.this, PersonalDetails.class);
            personal_details.launch(i_pd);
        });

        personal_details=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result) -> {
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                Intent get_personal_data= result.getData();
                String username= get_personal_data.getStringExtra("Username");
                String email=get_personal_data.getStringExtra("Email");
                String phone=get_personal_data.getStringExtra("Phone");

                intent1.putExtra("UserName", username);
                intent1.putExtra("Email", email);
                intent1.putExtra("Phone", phone);
            }
        });

        //Summary
        btn_summary_info.setOnClickListener(v -> {
            Intent i_sum= new Intent(Home.this, Summary.class);
            summary.launch(i_sum);
        });

        summary=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result)-> {
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                Intent get_summary=result.getData();
                String summ=get_summary.getStringExtra("Summary");

                intent1.putExtra("Summary_detail", summ);
            }
        });

        //Education
        btn_education_info.setOnClickListener(v -> {
            Intent i_edu= new Intent(Home.this, Education.class);
            education.launch(i_edu);
        });

        education=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result) -> {
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                Intent get_education=result.getData();
                String edu= get_education.getStringExtra("Education");

                intent1.putExtra("Education_detail", edu);
            }
        });

        //Experience
        btn_experience_info.setOnClickListener(v -> {
            Intent i_exp= new Intent(Home.this, Experience.class);
            work_experience.launch(i_exp);
        });

        work_experience=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)-> {
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                Intent get_experience=result.getData();
                String experience=get_experience.getStringExtra("Experience");

                intent1.putExtra("Experience_detail", experience);
            }
        });

        //certifications
        btn_certifications_info.setOnClickListener(v -> {
            Intent i_cert= new Intent(Home.this, Certifications.class);
            certifications.launch(i_cert);
        });

        certifications=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)-> {
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                Intent get_certificates=result.getData();
                String certificates=get_certificates.getStringExtra("Certificates");

                intent1.putExtra("Certificates_detail", certificates );
            }
        });

        //references
        btn_references_info.setOnClickListener(v -> {
            Intent i_ref= new Intent(Home.this, References.class);
            references.launch(i_ref);
        });

        references=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result) -> {
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                Intent get_reference_data= result.getData();
                String preview_reference_name=get_reference_data.getStringExtra("Reference_Name");
                String preview_reference_email=get_reference_data.getStringExtra("Reference_Email");
                String preview_reference_phone=get_reference_data.getStringExtra("Reference_Phone");
                String preview_reference_company=get_reference_data.getStringExtra("Reference_Company");

                intent1.putExtra("Reference_name", preview_reference_name);
                intent1.putExtra("Reference_email",preview_reference_email);
                intent1.putExtra("Reference_phone", preview_reference_phone);
                intent1.putExtra("Reference_company", preview_reference_company);
            }
        });


        btn_preview_info.setOnClickListener(v -> {
            startActivity(intent1);
        });




    }
}