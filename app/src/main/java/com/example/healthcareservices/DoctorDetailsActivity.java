package com.example.healthcareservices;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.healthcareservices.adapters.DoctorAdapter;
import com.example.healthcareservices.databinding.ActivityDoctorDetailsBinding;
import com.example.healthcareservices.doctordatabase.DoctorDatabase;
import com.example.healthcareservices.model.doctor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] list1 = {
            {"Doctor Name: Ngwen Japan","Hospital Address: Burnaby","Exp: 10 yrs","Phone:911","110"},
            {"Doctor Name: Gabriel Nah","Hospital Address: Vancouver","Exp: 13 yrs","Phone:911","120"},
            {"Doctor Name: Trab Niko","Hospital Address: New West","Exp: 20 yrs","Phone:911","100"},
            {"Doctor Name: Vqa Asg","Hospital Address: Richmond","Exp: 13 yrs","Phone:911","115"},
            {"Doctor Name: Hikori Tok","Hospital Address: Surrey","Exp: 8 yrs","Phone:911","200"},
    };
    TextView tv;
    Button btn;

    //create an empty list to store the selected type of doctor:
    String[][] doctor_chosen = {};
    HashMap<String,String> item;
    ArrayList carsurList;
    SimpleAdapter simpleAdapter;
    ActivityDoctorDetailsBinding binding;
    List<doctor> doctorList = new ArrayList<>();
    DoctorDatabase cdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);


        binding = ActivityDoctorDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        doctorList = readDoctorDatabase();
        cdb = Room.databaseBuilder(getApplicationContext(),DoctorDatabase.class,"doctors.db").build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                cdb.doctorDao().insertDoctorsFromList(doctorList);
                List<doctor> DoctorsDB = cdb.doctorDao().GetAllDoctors();
            }
        });

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians")==0 || title.compareTo("Dietician")==0 || title.compareTo("Dentist")==0){
            binding.recyclerViewDoctor.setAdapter(new DoctorAdapter(doctorList));
            binding.recyclerViewDoctor.setLayoutManager(new LinearLayoutManager(this));
        } else {
            doctor_chosen = list1;

            carsurList = new ArrayList();
            for(int i=0;i<doctor_chosen.length;i++){
                item = new HashMap<String,String>();
                item.put("line1",doctor_chosen[i][0]);
                item.put("line2",doctor_chosen[i][1]);
                item.put("line3",doctor_chosen[i][2]);
                item.put("line4",doctor_chosen[i][3]);
                item.put("line5","Charge: "+"CAD $"+doctor_chosen[i][4]);
                carsurList.add(item);
            }

            simpleAdapter = new SimpleAdapter(this,carsurList,
                    R.layout.multi_lines,
                    new String[]{"line1","line2","line3","line4","line5"},
                    new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
            ListView lst = findViewById(R.id.listViewDD);
            lst.setAdapter(simpleAdapter);

            lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                    it.putExtra("text1",title);
                    it.putExtra("text2",doctor_chosen[position][0]);
                    it.putExtra("text3",doctor_chosen[position][1]);
                    it.putExtra("text4",doctor_chosen[position][3]);
                    it.putExtra("text5",doctor_chosen[position][4]);
                    startActivity(it);
                }
            });
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });
    }

    //reading database file for Family Physicians:
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<doctor> readDoctorDatabase(){
        List<doctor> doctorList1 = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.familydoctors);
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