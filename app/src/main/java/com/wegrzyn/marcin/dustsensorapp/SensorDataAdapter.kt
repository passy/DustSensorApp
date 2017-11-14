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
        // TODO: Good place to start with Litho, isn't it?
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        val textDate: TextView = view.findViewById(R.id.text_date)
        val textPm25: TextView = view.findViewById(R.id.text_pm25)
        val textPm10: TextView = view.findViewById(R.id.text_pm25)
        val sensorData = getItem(position)

        textDate.text = SimpleDateFormat.getDateTimeInstance().format(sensorData.date)
        textPm25.text = sensorData.pm25.toString()
        textPm10.text = sensorData.pm10.toString()

        return view
    }
}
