package com.example.healthcareservices.interfaces;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.healthcareservices.model.doctor;

import java.util.List;

public interface DoctorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertDoctors(doctor...doctors);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertDoctorsFromList(List<doctor> doctorList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOneDoctor(doctor doctor);

    @Query("SELECT * FROM doctors")
    List<doctor> GetAllDoctors();

}
