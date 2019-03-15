package com.felipesilva.myseries.mvp.model

import android.util.Log
import android.util.Log.d
import com.felipesilva.myseries.webClient.service.ApiService
import com.felipesilva.myseries.data.Shows
import com.felipesilva.myseries.mvp.presenter.MainPresenter
import com.felipesilva.myseries.webClient.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainModel private constructor() {
    private val mainPresenter = MainPresenter.getInstance()

    companion object {
        private lateinit var mInstance : MainModel

        @Synchronized
        fun initializeInstance() {
            if (!::mInstance.isInitialized) {
                mInstance = MainModel()
            }
        }

        @JvmStatic
        @Synchronized
        fun getInstance(): MainModel {
            if (!::mInstance.isInitialized) {
                throw IllegalStateException(MainPresenter::class.java.simpleName + " is not initialized, call initializeInstance(..) method first.")
            }
            return mInstance
        }
    }

    @Synchronized
    fun loadData() {
        val retrofit = RetrofitConfig.getInstance()

        val api = retrofit.buildRetrofit()
            .create(ApiService::class.java)


        api.search().enqueue(object : Callback<MutableList<Shows>> {
            override fun onResponse(call: Call<MutableList<Shows>>, response: Response<MutableList<Shows>>) {
                mainPresenter.loadData(response.body())
            }

            override fun onFailure(call: Call<MutableList<Shows>>, t: Throwable) {
                Log.d("123felipe", "onFailure")
            }
        })
    }
}