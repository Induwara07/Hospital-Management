package com.example.hospital_management;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;

import java.util.ArrayList;

public class view_patient extends AppCompatActivity {

    TableLayout table_patient;
    SearchView sview;
    private DatabaseHelper P_DBHelper;
    private ArrayList<PatientUser> patientUserArrayList;

    @SuppressLint("App")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_patient);
        P_DBHelper = new DatabaseHelper(this);
        patientUserArrayList = P_DBHelper.getAllUsers();

        sview = (SearchView) findViewById(R.id.search_view_patient);
        sview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(view_patient.this, Search_patient.class);
                intent.putExtra("P_name", sview.getQuery().toString());
                startActivity(intent);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        table_patient = findViewById(R.id.view_tablep);
        table_patient.setStretchAllColumns(true);
        if (patientUserArrayList!=null) {
            for (int i = 0; i < patientUserArrayList.size(); i++) {

                TableRow row = new TableRow(this);
                row.setBackgroundColor(Color.parseColor("#FFFFFF"));
                final String id = Integer.toString(patientUserArrayList.get(i).getId());
                String name = patientUserArrayList.get(i).getName();
                String age = patientUserArrayList.get(i).getAge();

                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view_patient.this, edit_patient.class);
                        intent.putExtra("P_id", id);
                        startActivity(intent);
                    }
                });

                TextView textviewid = new TextView(this);
                textviewid.setText("   " + id);
                textviewid.setTextAppearance(getApplicationContext(), R.style.rowStyle1);
                TextView textviewname = new TextView(this);
                textviewname.setText("" + name);
                textviewname.setTextAppearance(getApplicationContext(), R.style.rowStyle2);
                TextView textviewage = new TextView(this);
                textviewname.setText("" + age);
                textviewname.setTextAppearance(getApplicationContext(), R.style.rowStyle3);

                row.setBottom(2);

                row.addView(textviewid);
                row.addView(textviewname);
                row.addView(textviewage);
                table_patient.addView(row);
            }
        } else {
            TableRow tablerowerror = new TableRow(this);
            tablerowerror.setBackgroundColor(Color.parseColor("#FFFFFF"));
            TextView textviewerror = new TextView(this);
            textviewerror.setText("No Patient");
            textviewerror.setTextAppearance(getApplicationContext(), R.style.rowStyle1);
            textviewerror.setGravity(Gravity.CENTER);
            tablerowerror.addView(textviewerror);
            table_patient.addView(tablerowerror);
        }
    }


}