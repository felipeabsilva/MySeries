package com.felipesilva.myseries.mvp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.felipesilva.myseries.data.db.TvMazeDatabase
import com.felipesilva.myseries.data.model.Show
import com.felipesilva.myseries.data.model.Shows
import com.felipesilva.myseries.mvp.MVP

class MainModel (val tvMazeDatabase: TvMazeDatabase) : MVP.MainModelImpl {
    private val seriesList = mutableListOf<Shows>()
    private val series = MutableLiveData<List<Shows>>()

    override fun makeCallSeriesList(name: String) {
        tvMazeDatabase.makeCallSeriesList(name)

        tvMazeDatabase.getSeriesList().observeForever { mSeries ->
            if (seriesList.isNotEmpty())
                seriesList.clear()

            seriesList.addAll(mSeries)
            series.value = seriesList
        }
    }

    override fun getSeriesList(): LiveData<List<Shows>> = series
}
