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

public class References extends AppCompatActivity {
    EditText et_name_ref, et_email_ref, et_phone_ref, et_company_ref;
    Button btn_save_reference, btn_cancel_reference;

    private void init() {
        et_name_ref = findViewById(R.id.name_ref);
        et_email_ref = findViewById(R.id.email_address_ref);
        et_phone_ref = findViewById(R.id.phone_number_ref);
        et_company_ref = findViewById(R.id.company_name_ref);
        btn_save_reference=findViewById(R.id.save_button_references);
        btn_cancel_reference=findViewById(R.id.cancel_button_references);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_references);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        btn_save_reference.setOnClickListener(v -> {
            String Ref_Name = et_name_ref.getText().toString().trim();
            String Ref_Email = et_email_ref.getText().toString().trim();
            String Ref_Phone = et_phone_ref.getText().toString().trim();
            String Ref_Company_Name = et_company_ref.getText().toString().trim();

            if (Ref_Name.isEmpty() || Ref_Email.isEmpty() || Ref_Phone.isEmpty() || Ref_Company_Name.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show();
            } else if (Ref_Name.length() < 3 || Ref_Email.length() < 5 || Ref_Phone.length() < 5 || Ref_Company_Name.length() < 3) {
                Toast.makeText(this, "Your word count is not appropriate", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Reference information saved successfully!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(References.this, Home.class);
                i.putExtra("Reference_Name", Ref_Name);
                i.putExtra("Reference_Email", Ref_Email);
                i.putExtra("Reference_Phone", Ref_Phone);
                i.putExtra("Reference_Company", Ref_Company_Name);
                setResult(RESULT_OK, i);
                finish();
            }
        });

        btn_cancel_reference.setOnClickListener(v -> {
            Toast.makeText(this, "Changes Discarded", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(References.this, Home.class);
            startActivity(i);
            finish();
        });
    }
}