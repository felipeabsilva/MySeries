package com.felipesilva.myseries.webClient.service

import com.felipesilva.myseries.data.Shows
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("shows?q=rick")
    fun search(): Call<MutableList<Shows>>

    @GET("shows")
    fun searchParameter(@Query("q") search: String): Call<MutableList<Shows>>
}