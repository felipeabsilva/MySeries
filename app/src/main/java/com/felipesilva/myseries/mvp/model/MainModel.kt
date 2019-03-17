package com.felipesilva.myseries.mvp.model

import android.view.View
import com.felipesilva.myseries.data.Shows
import com.felipesilva.myseries.mvp.MVP
import com.felipesilva.myseries.mvp.presenter.MainPresenter
import com.felipesilva.myseries.webClient.RetrofitConfig
import com.felipesilva.myseries.webClient.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainModel private constructor() : MVP.MainModelImpl {
    private val mainPresenter = MainPresenter.getInstance()

    companion object {
        private lateinit var mInstance: MainModel

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
    override fun loadData() {
        val retrofit = RetrofitConfig.getInstance()

        val api = retrofit.buildRetrofit()
            .create(ApiService::class.java)


        api.search().enqueue(object : Callback<MutableList<Shows>> {
            override fun onResponse(call: Call<MutableList<Shows>>, response: Response<MutableList<Shows>>) {
                mainPresenter.loadData(response.body())
            }

            override fun onFailure(call: Call<MutableList<Shows>>, t: Throwable) {
                mainPresenter.showMessage("A conexão falhou, tente novamente.")
            }
        })
    }

    @Synchronized
    override fun loadDataWithParameter(search: String) {
        val retrofit = RetrofitConfig.getInstance()

        val api = retrofit.buildRetrofit()
            .create(ApiService::class.java)


        api.searchParameter(search).enqueue(object : Callback<MutableList<Shows>> {
            override fun onResponse(call: Call<MutableList<Shows>>, response: Response<MutableList<Shows>>) {
                mainPresenter.loadData(response.body())
            }

            override fun onFailure(call: Call<MutableList<Shows>>, t: Throwable) {
                mainPresenter.showMessage("A conexão falhou, tente novamente.")
                mainPresenter.setRecyclerAndProgressViewVisibility(View.GONE, View.GONE)
            }
        })
    }
}