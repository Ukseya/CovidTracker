package com.example.covidtracker.service

import com.example.covidtracker.model.CovidDataModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface CovidApi {

    val country:String

@GET("v3/covid-19/countries")
fun getData(): Observable<List<CovidDataModel>>
//fun getData(): Call<List<CovidDataModel>>
}