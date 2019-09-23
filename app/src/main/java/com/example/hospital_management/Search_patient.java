package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class Search_patient extends AppCompatActivity {

    TableLayout TLsearch;
    SearchView searchV;
    private DatabaseHelper dbhelper;
    private ArrayList<PatientUser> patientUserArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_patient);

        dbhelper=new DatabaseHelper(this);
        String p_name=getIntent().getStringExtra("patient name");
        patientUserArrayList=dbhelper.searchPatients(p_name);

        searchV=findViewById(R.id.view_patient_search2);
        searchV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent=new Intent(Search_patient.this,Search_patient.class);
                intent.putExtra("patient name",searchV.getQuery().toString());
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        TLsearch=findViewById(R.id.view_tablep1);
        //TableLayout table = (TableLayout) findViewById(R.id.view_table);
        TLsearch.setStretchAllColumns(true);
        if(patientUserArrayList!=null) {
            for(int i=0;i<patientUserArrayList.size();i++)
            {
                TableRow row=new TableRow(this);

                row.setBackgroundColor(Color.parseColor("#FFFFFF"));
                final String id =Integer.toString( patientUserArrayList.get(i).getId());
                String name =patientUserArrayList.get(i).getName();
                String age =patientUserArrayList.get(i).getAge();
                String contNo=patientUserArrayList.get(i).getContNo();
                String gender =patientUserArrayList.get(i).getGender();
                String address=patientUserArrayList.get(i).getAddress();
                String diseases=patientUserArrayList.get(i).getDisease();
                String guardian=patientUserArrayList.get(i).getGurName();

                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(Search_patient.this,edit_patient.class);
                        intent.putExtra("p_id",id);
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

                row.addView(textviewid);
                row.addView(textviewname);
                row.addView(textviewage);
                TLsearch.addView(row);
            }
        }else{
            TableRow rowS = new TableRow(this);
            rowS.setBackgroundColor(Color.parseColor("#FFFFFF"));
            TextView tvid = new TextView(this);
            tvid.setText("No Patient");
            tvid.setTextAppearance(getApplicationContext(), R.style.rowStyle1);
            tvid.setGravity(Gravity.CENTER);
            rowS.addView(tvid);
            TLsearch.addView(rowS);
        }
    }
}
