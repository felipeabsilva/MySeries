package com.felipesilva.myseries.mvp

import android.app.Activity
import com.felipesilva.myseries.data.Shows
import com.felipesilva.myseries.mvp.view.MainActivity

interface MVP {
    interface MainViewImpl {
        fun getActivity() : Activity
        fun showData(shows: MutableList<Shows>)
        fun showMessage(message: String)
    }

    interface MainPresenterImpl {
        fun loadData(shows: MutableList<Shows>?)
        fun initializeModelInstance()
        fun setMainActivity(activity: MainActivity)
        fun listCardsShows()
        fun searchCardShows(search: String)
        fun showMessage(message: String)
        fun loadFavorites(): MutableSet<String>
    }

    interface MainModelImpl {
        fun loadData()
        fun loadDataWithParameter(search: String)
    }
}