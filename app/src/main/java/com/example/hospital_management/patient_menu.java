package com.example.hospital_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class patient_menu extends AppCompatActivity {
     Button add , view , home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_menu);

        add=(Button)findViewById(R.id.buttonaddp);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(patient_menu.this,add_patient.class);
                startActivity(intent);
            }
        });

        view= (Button) findViewById(R.id.buttonviewp2);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(patient_menu.this,view_patient.class);
                startActivity(intent);
            }
        });

        home=(Button)findViewById(R.id.buttonviewp);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(patient_menu.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
