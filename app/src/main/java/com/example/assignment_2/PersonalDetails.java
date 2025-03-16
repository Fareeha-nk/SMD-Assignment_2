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

public class PersonalDetails extends AppCompatActivity {
    EditText etName, etEmailAddress, etPhoneNumber;
    Button btn_save_personal_details, btn_cancel_personal_details;

    private void init(){
        etName=findViewById(R.id.name);
        etEmailAddress=findViewById(R.id.email_address);
        etPhoneNumber=findViewById(R.id.phone_number);
        btn_save_personal_details=findViewById(R.id.save_button_personal_details);
        btn_cancel_personal_details=findViewById(R.id.cancel_button_personal_details);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        btn_save_personal_details.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmailAddress.getText().toString().trim();
            String phone = etPhoneNumber.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            } else if (name.length()<3 || email.length()<5 || phone.length()<5 ) {
                Toast.makeText(this, "Your word count is not appropriate", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent = new Intent(PersonalDetails.this, Home.class);
                Toast.makeText(this, "Details Saved Successfully", Toast.LENGTH_SHORT).show();
                intent.putExtra("Username", name);
                intent.putExtra("Email", email);
                intent.putExtra("Phone", phone);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn_cancel_personal_details.setOnClickListener(v -> {
            Toast.makeText(this, "Changes Discarded!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PersonalDetails.this, Home.class);
            startActivity(intent);
            finish();
        });
    }
}