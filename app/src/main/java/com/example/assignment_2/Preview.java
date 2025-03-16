package com.example.assignment_2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Preview extends AppCompatActivity {
    ImageView iv_profile_picture_preview;
    TextView tv_username_preview, tv_email_preview, tv_phone_preview, tv_preview_summary, tv_preview_education, tv_preview_experience, tv_preview_certifications, tv_preview_reference_name, tv_preview_reference_email, tv_preview_reference_phone, tv_preview_reference_company_name;
    Uri preview_picture;
    Button btn_preview_share;
    ActivityResultLauncher<Intent> share_intent;

    private void init(){
        iv_profile_picture_preview=findViewById(R.id.profile_picture_preview);
        tv_username_preview=findViewById(R.id.preview_username);
        tv_email_preview=findViewById(R.id.preview_email);
        tv_phone_preview=findViewById(R.id.preview_phone_number);
        tv_preview_summary=findViewById(R.id.preview_summary);
        tv_preview_education=findViewById(R.id.preview_education);
        tv_preview_experience=findViewById(R.id.preview_experience);
        tv_preview_certifications=findViewById(R.id.preview_certifications);
        tv_preview_reference_name=findViewById(R.id.preview_reference_name);
        tv_preview_reference_email=findViewById(R.id.preview_reference_email);
        tv_preview_reference_phone=findViewById(R.id.preview_reference_phone);
        tv_preview_reference_company_name=findViewById(R.id.preview_reference_company);
        btn_preview_share=findViewById(R.id.preview_share);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        Intent i = getIntent();

        //profile_picture
        String pictureUriString = i.getStringExtra("picture");
        if (!pictureUriString.isEmpty()) {
            Uri preview_picture = Uri.parse(pictureUriString);
            iv_profile_picture_preview.setImageURI(preview_picture);
        } else {
            Toast.makeText(this, "No profile picture received!", Toast.LENGTH_SHORT).show();
        }

        //personal details
        String preview_username=i.getStringExtra("UserName");
        String preview_email=i.getStringExtra("Email");
        String preview_phone=i.getStringExtra("Phone");
        tv_username_preview.setText(preview_username);
        tv_email_preview.setText(preview_email);
        tv_phone_preview.setText(preview_phone);

        //summary
        String preview_summary=i.getStringExtra("Summary_detail");
        tv_preview_summary.setText(preview_summary);

        //education
        String preview_education=i.getStringExtra("Education_detail");
        tv_preview_education.setText(preview_education);

        //Experience
        String preview_experience=i.getStringExtra("Experience_detail");
        tv_preview_experience.setText(preview_experience);

        //Certifications
        String preview_certifications=i.getStringExtra("Certificates_detail");
        tv_preview_certifications.setText(preview_certifications);

        //References
        String preview_references_name=i.getStringExtra("Reference_name");
        String preview_references_email=i.getStringExtra("Reference_email");
        String preview_references_phone=i.getStringExtra("Reference_phone");
        String preview_references_company=i.getStringExtra("Reference_company");
        tv_preview_reference_name.setText(preview_references_name);
        tv_preview_reference_email.setText(preview_references_email);
        tv_preview_reference_phone.setText(preview_references_phone);
        tv_preview_reference_company_name.setText(preview_references_company);


        share_intent=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result)-> {
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                Toast.makeText(this, "Successful!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Unsuccessful!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_preview_share.setOnClickListener(v -> {
            Intent shareIntent=new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            share_intent.launch(shareIntent);
        });
    }
}