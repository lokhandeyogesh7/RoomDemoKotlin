package com.example.myapplication.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Patient")
open class Patient {

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    @ColumnInfo(name = "date")
    var date: String = ""

    @ColumnInfo(name = "patient_name")
    var patientName: String = ""

    @ColumnInfo(name = "disease")
    var disease: String = ""

    @ColumnInfo(name = "fees")
    var fees: Int = 0

    @ColumnInfo(name = "gender")
    var gender: String = ""

    @ColumnInfo(name = "mobile")
    var mobileNumber: String = ""

}