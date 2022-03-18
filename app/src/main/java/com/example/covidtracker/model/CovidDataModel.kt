package com.example.covidtracker.model
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