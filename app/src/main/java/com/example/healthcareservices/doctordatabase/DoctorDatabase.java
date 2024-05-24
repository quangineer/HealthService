package com.example.healthcareservices.doctordatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.healthcareservices.interfaces.DoctorDao;
import com.example.healthcareservices.model.doctor;

@Database(entities = {doctor.class}, version = 1,exportSchema = false)
public abstract class DoctorDatabase extends RoomDatabase {
    public  abstract DoctorDao doctorDao();
}
