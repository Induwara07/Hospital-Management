package com.example.hospital_management;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class edit_patient extends AppCompatActivity {

    TextView update_name,update_age, update_contact , update_gender , update_address , update_diseases, update_gurdian;
    Button updateButton, DeleteButton;
    String id;
    ArrayList<PatientUser> patientArray;
    DatabaseHelper dbHelper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_patient);

        update_name=findViewById(R.id.up_name);
        update_age=findViewById(R.id.up_age);
        update_contact=findViewById(R.id.up_contact);
        update_gender=findViewById(R.id.up_gender);
        update_address=findViewById(R.id.up_address);
        update_diseases=findViewById(R.id.up_diseases);
        update_gurdian=findViewById(R.id.up_gurdian);

        updateButton=findViewById(R.id.up_button);
        DeleteButton=findViewById(R.id.del_button);

        //id=getIntent().getStringExtra("p_id");
        id="7";
        patientArray= dbHelper.getPatient(id);
        //update_name.setText("test");

        final int id=patientArray.get(0).getId();
        String name=patientArray.get(0).getName();
        String age=patientArray.get(0).getAge();
        String contact=patientArray.get(0).getContNo();
        String gender=patientArray.get(0).getGender();
        String address=patientArray.get(0).getAddress();
        String diseases=patientArray.get(0).getDisease();
        String guardian=patientArray.get(0).getGurName();

        update_name.setText(patientArray.get(0).getName());
        update_age.setText(""+age);
        update_contact.setText(""+contact);
        update_gender.setText(""+gender);
        update_address.setText(""+address);
        update_diseases.setText(""+diseases);
        update_gurdian.setText(""+guardian);

        final String new_name=update_name.getText().toString();
        final String new_age=update_age.getText().toString();
        final String new_contact=update_contact.getText().toString();
        final String new_gender=update_gender.getText().toString();
        final String new_address=update_address.getText().toString();
        final String new_diseases=update_diseases.getText().toString();
        final String new_gurdian=update_gurdian.getText().toString();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbHelper.updatePatient(id, update_name.getText().toString(), update_age.getText().toString(),update_contact.getText().toString(),update_gender.getText().toString(), update_address.getText().toString(),update_diseases.getText().toString(),update_gurdian.getText().toString())){
                    Toast.makeText(edit_patient.this,"Updated Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(edit_patient.this,view_patient.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(edit_patient.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });

        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_box=new AlertDialog.Builder(edit_patient.this);
                alert_box.setMessage("You want to delete this patient ?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (dbHelper.deletePatient(id)){
                                    Toast.makeText(edit_patient.this,"Deleted Successfully",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(edit_patient.this,view_patient.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(edit_patient.this,"Delete Faild",Toast.LENGTH_SHORT).show();

                                }
                            }
                        }).setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }) ;
                AlertDialog alert = alert_box.create();
                alert.setTitle("Alert !!!");
                alert.show();

            }
        });
    }


}
