package com.wegrzyn.marcin.dustsensorapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.main_activity_layout.*

class MainActivity : AppCompatActivity() {

    private val sensorDataList = ArrayList<SensorData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)

        val sensorDataAdapter = SensorDataAdapter(this, sensorDataList)
        list_main.adapter = sensorDataAdapter

        val firebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference = firebaseDatabase.getReference(SENSOR_DATA)
        val query = databaseReference.limitToLast(6)
        query.addChildEventListener(object : ChildEventListener {

            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val sensorData = dataSnapshot.getValue<SensorData>(SensorData::class.java)
                sensorDataAdapter.insert(sensorData, 0)

                setData(sensorData)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                Log.d(TAG, "onChildChange")
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                Log.d(TAG, "onChildRemoved")
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
                Log.d(TAG, "onChildMoved")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(TAG, "onCanceled")
            }
        })

        list_main.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ -> setData(sensorDataList[i]) }
    }

    private fun setData(sensorData: SensorData?) {
        if (sensorData == null) return
        text_pm25.text = sensorData.pm25.toString()
        text_pm10.text = sensorData.pm10.toString()
        text_date.text = sensorData.date.toString()
        progress_list.visibility = View.INVISIBLE
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private val SENSOR_DATA = "SensorData"
    }
}
