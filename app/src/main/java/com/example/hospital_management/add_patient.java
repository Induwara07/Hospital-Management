package com.example.hospital_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class add_patient extends AppCompatActivity {

    private Button register;
    private EditText pname, page, pcontact,pgender, paddress, pdiseases,pguardian;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient);
        databaseHelper = new DatabaseHelper(this);
        register=(Button) findViewById(R.id.buttonreg);
        pname = (EditText) findViewById(R.id.pname);
        page = (EditText) findViewById(R.id.page);
        pcontact = (EditText) findViewById(R.id.pcontact);
        pgender= (EditText) findViewById(R.id.pgender);
        paddress = (EditText) findViewById(R.id.paddress);
        pdiseases = (EditText) findViewById(R.id.pdiseases);
        pguardian= (EditText) findViewById(R.id.pguardian);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Empty Patient Field Validate
                if(pname.getText().toString().equals("")||page.getText().toString().equals("")||paddress.getText().toString()
                        .equals("")||pcontact.getText().toString().equals("")||pdiseases.getText().toString().equals("")||pgender.getText()
                        .toString().equals("")||pguardian.getText().toString().equals("")){
                    Toast.makeText(add_patient.this, "Fill The Empty Fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(page.getText().toString().length()>2){
                    Toast.makeText(add_patient.this, "Age is Wrong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pcontact.getText().toString().length()>10 ||pcontact.getText().toString().length()< 10 ){
                    Toast.makeText(add_patient.this, "Phone Number is Wrong", Toast.LENGTH_SHORT).show();
                    return;
                }
                databaseHelper.addPatientDetail(pname.getText().toString(),page.getText().toString(),paddress.getText().toString()
                        ,pcontact.getText().toString(),pdiseases.getText().toString(),pgender.getText().toString(),pguardian.getText().toString());
                pname.setText("");
                page.setText("");
                paddress.setText("");
                pcontact.setText("");
                pdiseases.setText("");
                pgender.setText("");
                pguardian.setText("");
                Toast.makeText(add_patient.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(add_patient.this, add_patient.class);
                startActivity(intent);
            }
        });
    }

}
