package com.felipesilva.myseries

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.d
import com.felipesilva.myseries.adapter.ShowCardAdapter
import com.felipesilva.myseries.data.Show
import com.felipesilva.myseries.data.Shows
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.tvmaze.com/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.search().enqueue(object : Callback<MutableList<Shows>> {
            override fun onResponse(call: Call<MutableList<Shows>>, response: Response<MutableList<Shows>>) {
                showData(response.body())
                d("123felipe","image: ${response.body()!![1].show.image}")
            }

            override fun onFailure(call: Call<MutableList<Shows>>, t: Throwable) {
                d("123felipe", "onFailure")
            }

        })
    }

    private fun showData(shows: MutableList<Shows>?) {

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)

            shows?.let {
                adapter = ShowCardAdapter(it)
            }

        }
    }
}
