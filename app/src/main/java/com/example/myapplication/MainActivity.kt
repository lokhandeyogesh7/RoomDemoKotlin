package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.Patient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_data.view.*

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
            for (i in 0 until data.size) {
                rvData.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
                val adapter = RecyclerViewAdapter(data as ArrayList<Patient>)
                rvData.adapter = adapter
            }
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

    inner class RecyclerViewAdapter(var data: ArrayList<Patient>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

        var mContext: Context? = null

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val dData = data[position]
            holder.tvPatientName.text = dData.patientName
            holder.tvGender.text = dData.gender
            holder.tvDate.text = dData.date
            holder.tvDisease.text = dData.disease
            holder.tvMobile.text = dData.mobileNumber
            holder.tvFees.text = dData.fees.toString()

            holder.ivDelete.setOnClickListener {
                val db = AppDatabase.getAppDatabase(mContext!!)
                db.patientDao().deletePatient(dData)
                data.remove(dData)
                notifyDataSetChanged()
            }
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvPatientName = itemView.findViewById<TextView>(R.id.tvPatientName)
            val tvGender = itemView.findViewById<TextView>(R.id.tvGender)
            val tvDate = itemView.findViewById<TextView>(R.id.tvDate)
            val tvDisease = itemView.findViewById<TextView>(R.id.tvDisease)
            val tvMobile = itemView.findViewById<TextView>(R.id.tvMobile)
            val tvFees = itemView.findViewById<TextView>(R.id.tvFees)
            val ivDelete = itemView.findViewById<ImageView>(R.id.ivDelete)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            mContext = parent.context
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_data, parent, false)


            return ViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return data.size
        }
    }
}
