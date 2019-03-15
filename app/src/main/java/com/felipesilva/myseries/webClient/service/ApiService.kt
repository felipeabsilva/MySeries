package com.felipesilva.myseries.webClient.service

import com.felipesilva.myseries.data.Shows
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("shows?q=rick")
    fun search(): Call<MutableList<Shows>>
}