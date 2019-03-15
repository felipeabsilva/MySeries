package com.felipesilva.myseries.webClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig private constructor() {

    companion object {
        private val mInstance = RetrofitConfig()

        @Synchronized
        fun getInstance() = mInstance
    }

    fun buildRetrofit() = Retrofit.Builder()
            .baseUrl("http://api.tvmaze.com/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}