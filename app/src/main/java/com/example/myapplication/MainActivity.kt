package com.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.Patient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = AppDatabase.getAppDatabase(this)
        btnAdd.setOnClickListener {
            addDataToLocalDb(db)
        }

        btnPrintData.setOnClickListener {
            val data = db.patientDao().getAll()
            val strBuilder = StringBuilder()
            for (i in 0 until data.size) {
                strBuilder.append("${data[i].uid},${data[i].patientName}\n")
            }
            tvData.text = strBuilder
        }
    }

    private fun addDataToLocalDb(db: AppDatabase) {

        val patient = Patient()
        patient.date = "today"
        patient.patientName = "demo name"
        patient.fees = 100
        patient.disease = "Maleria"
        patient.mobileNumber = "9876543210"
        patient.gender = "male"

        db.patientDao().insertPatient(patient)
    }
}
