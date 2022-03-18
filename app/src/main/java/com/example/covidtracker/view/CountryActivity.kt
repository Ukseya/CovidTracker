package com.example.covidtracker.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.covidtracker.R
import java.util.*

class CountryActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        val country = intent.getStringExtra("Country").toString()
        val covidCaseToday = intent.getIntExtra("covidCaseToday",1234567).toString()
        val covidCaseTotal = intent.getIntExtra("covidCaseTotal",1234567).toString()
        val covidDeathsToday = intent.getIntExtra("covidDeathsToday",1234567).toString()
        val covidDeathsTotal = intent.getIntExtra("covidDeathsTotal",1234567).toString()
        val covidRecoveredToday = intent.getIntExtra("covidRecoveredToday",1234567).toString()
        val covidRecoveredTotal = intent.getIntExtra("covidRecoveredTotal",1234567).toString()
        val covidActive = intent.getIntExtra("covidActive",1234567).toString()
        val tvCountry = findViewById<TextView>(R.id.countryName)
        val tvNewCases = findViewById<TextView>(R.id.todayCases)
        val tvCases = findViewById<TextView>(R.id.totalCases)
        val tvNewDeath = findViewById<TextView>(R.id.todayDeaths)
        val tvDeaths = findViewById<TextView>(R.id.totalDeaths)
        val tvNewRecovered = findViewById<TextView>(R.id.todayRecovered)
        val tvRecovered = findViewById<TextView>(R.id.totalRecovered)
        val tvActive = findViewById<TextView>(R.id.activeCases)
        replaceTextView(tvCountry,country)
        replaceTextView(tvNewCases,covidCaseToday)
        replaceTextView(tvCases,covidCaseTotal)
        replaceTextView(tvNewDeath,covidDeathsToday)
        replaceTextView(tvDeaths,covidDeathsTotal)
        replaceTextView(tvNewRecovered,covidRecoveredToday)
        replaceTextView(tvRecovered,covidRecoveredTotal)
        replaceTextView(tvActive,covidActive)

        Toast.makeText(this,"Clicked: ${country}, New Cases Today: ${covidCaseToday} , Recovered Today: ${covidRecoveredToday} ",Toast.LENGTH_SHORT).show()



    }

    fun returnMain(view: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    fun replaceTextView(view: TextView, text: String){
        view.text = text.toString()
    }
}