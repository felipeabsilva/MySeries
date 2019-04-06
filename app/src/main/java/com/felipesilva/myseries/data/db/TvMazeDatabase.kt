package com.felipesilva.myseries.data.db

import androidx.lifecycle.MutableLiveData
import com.felipesilva.myseries.data.model.Shows

interface TvMazeDatabase {
    fun makeCallSeriesList(name: String)
    fun loadDataSeriesList(shows: List<Shows>)
    fun getSeriesList() : MutableLiveData<List<Shows>>
}