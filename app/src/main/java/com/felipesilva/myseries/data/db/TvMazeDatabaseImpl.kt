package com.felipesilva.myseries.data.db

import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import com.felipesilva.myseries.data.model.Show
import com.felipesilva.myseries.data.model.Shows
import com.felipesilva.myseries.data.remote.ApiService
import com.felipesilva.myseries.data.remote.retrofit.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvMazeDatabaseImpl(private val retrofitConfig: RetrofitConfig) : TvMazeDatabase {
    private val seriesList = mutableListOf<Shows>()
    private val series = MutableLiveData<List<Shows>>()

    override fun makeCallSeriesList(name: String) {
        val api = retrofitConfig.buildRetrofit()
            .create(ApiService::class.java)

        api.search(name).enqueue(object : Callback<List<Shows>> {
            override fun onResponse(call: Call<List<Shows>>, response: Response<List<Shows>>) {
                response.body()?.let {
                    loadDataSeriesList(it)
                }
            }

            override fun onFailure(call: Call<List<Shows>>, t: Throwable) {
                d("123felipe","Connection failed")
                d("123felipe",t.message)
            }

        })
    }

    override fun loadDataSeriesList(shows: List<Shows>) {
        if (seriesList.isNotEmpty())
            seriesList.clear()

        seriesList.addAll(shows)
        series.value = shows
    }

    override fun getSeriesList() : MutableLiveData<List<Shows>> = series

}
