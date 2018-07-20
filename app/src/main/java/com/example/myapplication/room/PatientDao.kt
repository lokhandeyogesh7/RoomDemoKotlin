package com.example.myapplication.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PatientDao {

    @Query("SELECT * FROM Patient")
    fun getAll(): List<Patient>


    @Insert
    fun insertPatient(patient: Patient)

}