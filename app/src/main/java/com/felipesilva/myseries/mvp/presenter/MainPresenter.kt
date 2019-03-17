package com.felipesilva.myseries.mvp.presenter

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import com.felipesilva.myseries.data.Shows
import com.felipesilva.myseries.features.FavoriteShow
import com.felipesilva.myseries.mvp.MVP
import com.felipesilva.myseries.mvp.model.MainModel
import com.felipesilva.myseries.mvp.view.MainActivity

class MainPresenter private constructor() : MVP.MainPresenterImpl {
    private lateinit var mainModel: MainModel
    private val listShows = mutableListOf<Shows>()
    private lateinit var mainView: MVP.MainViewImpl

    companion object {
        private lateinit var mInstance: MainPresenter

        @Synchronized
        fun initializeInstance() {
            if (!::mInstance.isInitialized) {
                mInstance = MainPresenter()
            }
        }

        @JvmStatic
        @Synchronized
        fun getInstance(): MainPresenter {
            if (!::mInstance.isInitialized) {
                throw IllegalStateException(MainPresenter::class.java.simpleName + " is not initialized, call initializeInstance(..) method first.")
            }
            return mInstance
        }
    }

    @Synchronized
    override fun loadData(shows: MutableList<Shows>?) {
        if (listShows.isNotEmpty())
            listShows.clear()

        if (shows is MutableList<Shows>)
            listShows.addAll(shows)

        if (listShows.isNotEmpty()) {
            mainView.showData(listShows)
        } else
            showMessage("Busca não encontrada!")
    }

    override fun initializeModelInstance() {
        MainModel.initializeInstance()
        mainModel = MainModel.getInstance()
    }

    override fun setMainActivity(activity: MainActivity) {
        mainView = activity
    }

    override fun listCardsShows() {
        mainModel.loadData()
    }

    override fun searchCardShows(search: String) {
        mainModel.loadDataWithParameter(search)
    }

    override fun showMessage(message: String) {
        mainView.showMessage(message)
    }

    override fun loadFavorites(): MutableSet<String> = FavoriteShow.getFavorites(mainView.getActivity())

}