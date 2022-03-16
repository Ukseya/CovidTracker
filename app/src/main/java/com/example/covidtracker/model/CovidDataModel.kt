package com.example.covidtracker.model

import java.net.URL

data class CovidDataModel(
    val country:String,
    val cases: Int,
    val todayCases: Int,
    val deaths: Int,
    val todayDeaths: Int,
    val recovered: Int,
    val todayRecovered: Int,
    val active: Int
)