package com.wegrzyn.marcin.dustsensorapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import com.google.firebase.database.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null

    private val sensorDataList = ArrayList<SensorData>()
    private var sensorDataAdapter: SensorDataAdapter? = null

    private var listView: ListView? = null

    private var PM2txt: TextView? = null
    private var PM10txt: TextView? = null
    private var DataTxt: TextView? = null

    private var progressBar: ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)

        listView = findViewById(R.id.listView)

        PM2txt = findViewById(R.id.PM2TV)
        PM10txt = findViewById(R.id.PM10TV)
        DataTxt = findViewById(R.id.DataTV)

        progressBar = findViewById(R.id.progress)


        sensorDataAdapter = SensorDataAdapter(this, sensorDataList)
        listView!!.adapter = sensorDataAdapter

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.getReference(SENSOR_DATA)
        val query = databaseReference!!.limitToLast(6)
        query.addChildEventListener(object : ChildEventListener {

            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val sensorData = dataSnapshot.getValue<SensorData>(SensorData::class.java)
                sensorDataAdapter!!.add(sensorData)

                setData(sensorData)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                Log.d(Tag, "onChildChange")
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                Log.d(Tag, "onChildRemoved")
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
                Log.d(Tag, "onChildMoved")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(Tag, "onCanceled")
            }
        })

        listView!!.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l -> setData(sensorDataList[i]) }
    }

    private fun setData(sensorData: SensorData?) {
        PM2txt!!.text = sensorData!!.pM2.toString()
        PM10txt!!.text = sensorData.pM10.toString()
        DataTxt!!.text = sensorData.date.toString()
        progressBar!!.visibility = View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.exit) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {

        private val Tag = MainActivity::class.java!!.simpleName

        private val SENSOR_DATA = "SensorData"
    }
}
