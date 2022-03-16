package com.example.covidtracker.adapter

import android.inputmethodservice.Keyboard
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtracker.R
import com.example.covidtracker.model.CovidDataModel
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(private val covidData: ArrayList<CovidDataModel>, private val listener:Listener): RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {
    interface Listener{
        fun onItemClick(covid:CovidDataModel)
    }

    class RowHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(covid: CovidDataModel,position: Int,listener: Listener){
            itemView.setOnClickListener { listener.onItemClick(covid) }
            itemView.country_name.text = covid.country
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.RowHolder, position: Int) {
        holder.bind(covidData[position],position,listener)
    }

    override fun getItemCount(): Int {
        return covidData.size
    }
}