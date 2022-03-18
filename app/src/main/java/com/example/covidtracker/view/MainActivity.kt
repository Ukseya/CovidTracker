package com.example.covidtracker.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtracker.R
import com.example.covidtracker.adapter.RecyclerViewAdapter
import com.example.covidtracker.model.CovidDataModel
import com.example.covidtracker.service.CovidApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {

    private val BASE_URL = "https://disease.sh/"
    private var covidDatas: ArrayList<CovidDataModel>?= null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    private var hashCovidData: HashMap<String, Int>? = null

    //Disposable
    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //https://disease.sh/v3/covid-19/countries to get countries
        //https://disease.sh/v3/covid-19/countries/{country} to get country specific data

        compositeDisposable = CompositeDisposable()

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        loadData()


    }

    fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CovidApi::class.java)

        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(
            AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))


        /*val service = retrofit.create(CovidApi::class.java)
        val call = service.getData()
        call.enqueue(object: Callback<List<CovidDataModel>>{
            override fun onResponse(
                call: Call<List<CovidDataModel>>,
                response: Response<List<CovidDataModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let{
                        covidDatas = ArrayList(it)
                        /*for (covidData:CovidDataModel in covidDatas!!){
                            println(covidData.country)
                            println(covidData.cases)

                        }*/
                        println(covidDatas!![0])


                    }
                }


            }

            override fun onFailure(call: Call<List<CovidDataModel>>, t: Throwable) {t.printStackTrace()}
        })*/

    }

    private fun handleResponse(covid:List<CovidDataModel>){
        covidDatas = ArrayList(covid)
        covidDatas?.let {
            recyclerViewAdapter =
                RecyclerViewAdapter(it,this@MainActivity)
            recyclerView.adapter = recyclerViewAdapter
        }
    }

    override fun onItemClick(covid: CovidDataModel) {
        val intent = Intent(this,CountryActivity::class.java)
        intent.putExtra("Country",covid.country)
        intent.putExtra("covidCaseToday",covid.todayCases)
        intent.putExtra("covidCaseTotal",covid.cases)
        intent.putExtra("covidDeathsToday",covid.todayDeaths)
        intent.putExtra("covidDeathsTotal",covid.deaths)
        intent.putExtra("covidRecoveredToday",covid.todayRecovered)
        intent.putExtra("covidRecoveredTotal",covid.recovered)
        intent.putExtra("covidActive",covid.active)
        startActivity(intent)
    }

    fun getData():HashMap<String,Int>?{
        return hashCovidData
    }
}