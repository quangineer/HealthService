package com.example.healthcareservices;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.healthcareservices.model.doctor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DoctorDetailsActivity extends AppCompatActivity {

    TextView tv;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });
    }

    //reading database file for Family Physicians:
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<doctor> readFamilyPhysiciansData(){
        List<doctor> doctorList1 = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.FamilyPhysicians);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String doctorLine;
        try{
            while((doctorLine = reader.readLine()) != null){
                String[] eachDoctorField = doctorLine.split(",");
                int doctorId = Integer.parseInt(eachDoctorField[0]);
                String doctorActiveDate = eachDoctorField[1];
                String doctorName = eachDoctorField[2];
                String doctorAddress = eachDoctorField[3];
                String doctorPhone = eachDoctorField[4];
                double doctorPrice = Double.parseDouble(eachDoctorField[5]);

                doctor eachDoctor = new doctor(doctorId,doctorActiveDate,doctorName,doctorAddress,doctorPhone,doctorPrice);
                doctorList1.add(eachDoctor);
            }
        } catch (IOException error){
            throw new RuntimeException(error);
        }
        return doctorList1;
    }
}