package com.wegrzyn.marcin.dustsensorapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.text.SimpleDateFormat

class SensorDataAdapter(context: Context, objects: List<SensorData>) : ArrayAdapter<SensorData>(context, 0, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        val dateTextView = view.findViewById<TextView>(R.id.Data)
        val pm2TextView = view.findViewById<TextView>(R.id.PM2)
        val pm10TextView = view.findViewById<TextView>(R.id.PM10)

        val sensorData = getItem(position)

        val datestring = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sensorData!!.date)

        dateTextView.text = datestring
        val pm2 = sensorData.pm25
        val pm10 = sensorData.pm10
        pm2TextView.text = pm2.toString()
        pm10TextView.text = pm10.toString()
        return view
    }

}
