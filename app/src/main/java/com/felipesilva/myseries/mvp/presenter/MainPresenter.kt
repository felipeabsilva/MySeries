package com.felipesilva.myseries.mvp.presenter

import androidx.lifecycle.LiveData
import com.felipesilva.myseries.data.model.Shows
import com.felipesilva.myseries.mvp.MVP

class MainPresenter(private val mainModelImpl: MVP.MainModelImpl) : MVP.MainPresenterImpl {
    init {
        mainModelImpl.makeCallSeriesList("rick")
    }

    override fun getSeriesList(): LiveData<List<Shows>> = mainModelImpl.getSeriesList()

    override fun makeCallSeriesList(name: String) = mainModelImpl.makeCallSeriesList(name)

}
