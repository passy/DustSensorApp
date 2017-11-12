package com.wegrzyn.marcin.dustsensorapp

import java.util.Date

class SensorData {

    var pM2: Float = 0.toFloat()
    var pM10: Float = 0.toFloat()
    var date: Date? = null

    constructor(PM2: Float, PM10: Float, date: Date) {
        this.pM2 = PM2
        this.pM10 = PM10
        this.date = date
    }

    constructor() {
        pM10 = 0f
        pM2 = 0f
    }


}
