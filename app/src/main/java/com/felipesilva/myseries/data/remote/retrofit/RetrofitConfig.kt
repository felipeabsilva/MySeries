package com.felipesilva.myseries.data.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig() {
    fun buildRetrofit() = Retrofit.Builder()
            .baseUrl("http://api.tvmaze.com/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}