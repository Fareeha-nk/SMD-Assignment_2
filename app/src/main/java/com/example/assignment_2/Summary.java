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

public class Summary extends AppCompatActivity {
    EditText etSummary_Card;
    Button btn_save_summary, btn_cancel_summary;

    private void init(){
        etSummary_Card=findViewById(R.id.summary_card);
        btn_save_summary=findViewById(R.id.save_button_summary);
        btn_cancel_summary=findViewById(R.id.cancel_button_summary);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        btn_save_summary.setOnClickListener(v -> {
            String Summary= etSummary_Card.getText().toString().trim();
            if(Summary.isEmpty()){
                Toast.makeText(this, "Please fill the field!", Toast.LENGTH_SHORT).show();
            }
            else if(Summary.length()<5 || Summary.length()>200){
                Toast.makeText(this, "Your word count is not appropriate!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Summary saved successfully!", Toast.LENGTH_SHORT).show();
                Intent i= new Intent(Summary.this, Home.class);
                i.putExtra("Summary", Summary);
                setResult(RESULT_OK, i);
                finish();
            }
        });

        btn_cancel_summary.setOnClickListener(v -> {
            Toast.makeText(this, "Changes Discarded!", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(Summary.this, Home.class);
            startActivity(i);
            finish();
        });
    }
}