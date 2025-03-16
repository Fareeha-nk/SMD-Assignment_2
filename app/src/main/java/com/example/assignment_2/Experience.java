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

public class Experience extends AppCompatActivity {
    EditText et_Experience_Card;
    Button btn_save_experience, btn_cancel_experience;

    private void init(){
        et_Experience_Card=findViewById(R.id.experience_card);
        btn_save_experience=findViewById(R.id.save_button_experience);
        btn_cancel_experience=findViewById(R.id.cancel_button_experience);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_experience);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        btn_save_experience.setOnClickListener(v -> {
            String Experience= et_Experience_Card.getText().toString().trim();
            if(Experience.isEmpty()){
                Toast.makeText(this, "Please fill the field!", Toast.LENGTH_SHORT).show();
            }
            else if(Experience.length()<5 || Experience.length()>70){
                Toast.makeText(this, "Your word count is not appropriate", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Experience information saved successfully", Toast.LENGTH_SHORT).show();
                Intent i= new Intent(Experience.this, Home.class);
                startActivity(i);
                finish();
            }
        });

        btn_cancel_experience.setOnClickListener(v -> {
            Toast.makeText(this, "Changes Discarded", Toast.LENGTH_SHORT).show();
            Intent i= new Intent(Experience.this, Home.class);
            startActivity(i);
            finish();
        });
    }
}