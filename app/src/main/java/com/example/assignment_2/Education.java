package com.example.assignment_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Education extends AppCompatActivity {
    EditText etEducation_Card;
    Button btn_save_education, btn_cancel_education;

    private void init(){
        etEducation_Card=findViewById(R.id.education_card);
        btn_save_education=findViewById(R.id.save_button_education);
        btn_cancel_education=findViewById(R.id.cancel_button_education);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_education);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        btn_save_education.setOnClickListener(v -> {
            String Education= etEducation_Card.getText().toString().trim();
            if(Education.isEmpty()){
                Toast.makeText(this, "Please fill the field!", Toast.LENGTH_SHORT).show();
            }
            else if(Education.length()<5){
                Toast.makeText(this, "Your word count is not appropriate", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Education information saved successfully", Toast.LENGTH_SHORT).show();
                Intent i= new Intent(Education.this, Home.class);
                startActivity(i);
                finish();
            }
        });

        btn_cancel_education.setOnClickListener(v -> {
            Toast.makeText(this, "Changes Discarded", Toast.LENGTH_SHORT).show();
            Intent i= new Intent(Education.this, Home.class);
            startActivity(i);
            finish();
        });
    }
}