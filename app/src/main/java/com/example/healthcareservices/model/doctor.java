package com.example.healthcareservices.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "doctors")
public class doctor {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name="doctorId")
    private int doctorId;

    @ColumnInfo(name="doctorActiveDate")
    private String doctorActiveDate;

    @ColumnInfo(name="doctorName")
    private String doctorName;

    @ColumnInfo(name="doctorAddress")
    private String doctorAddress;

    @ColumnInfo(name="doctorPhone")
    private String doctorPhone;

    @ColumnInfo(name="doctorPrice")
    private double doctorPrice;

    public doctor(@NonNull int doctorId, String doctorActiveDate, String doctorName, String doctorAddress, String doctorPhone, double doctorPrice) {
        this.doctorId = doctorId;
        this.doctorActiveDate = doctorActiveDate;
        this.doctorName = doctorName;
        this.doctorAddress = doctorAddress;
        this.doctorPhone = doctorPhone;
        this.doctorPrice = doctorPrice;
    }

    public doctor() {
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorActiveDate() {
        return doctorActiveDate;
    }

    public void setDoctorActiveDate(String doctorActiveDate) {
        this.doctorActiveDate = doctorActiveDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorAddress() {
        return doctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public double getDoctorPrice() {
        return doctorPrice;
    }

    public void setDoctorPrice(double doctorPrice) {
        this.doctorPrice = doctorPrice;
    }
}
