package com.wegrzyn.marcin.dustsensorapp

import java.time.Instant
import java.util.Date

// Firebase requires an empty-argument constructor, so we have to provide default values for
// every field here.
class SensorData(val pm25: Float = 0f, val pm10: Float = 0f, val date: Date = Date(0))
