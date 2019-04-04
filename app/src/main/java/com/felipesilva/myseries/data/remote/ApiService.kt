package com.felipesilva.myseries.data.remote

import com.felipesilva.myseries.data.model.Shows
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("shows")
    fun search(@Query("q") name: String): Call<List<Shows>>
}