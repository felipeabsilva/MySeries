package com.felipesilva.myseries.mvp.presenter

import android.app.Activity
import android.util.Log.d
import android.view.inputmethod.InputMethodManager
import com.felipesilva.myseries.mvp.view.MainActivity
import com.felipesilva.myseries.data.Shows
import com.felipesilva.myseries.features.FavoriteShow
import com.felipesilva.myseries.mvp.model.MainModel

class MainPresenter private constructor() {
    private lateinit var mainModel : MainModel
    private val listShows = mutableListOf<Shows>()
    private lateinit var mainView : MainActivity

    companion object {
        private lateinit var mInstance : MainPresenter

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

    fun initializeModelInstance() {
        MainModel.initializeInstance()
        mainModel = MainModel.getInstance()
    }

    fun setActivity(activity: MainActivity) {
        mainView = activity
    }

    fun listCardsShows(){
        mainModel.loadData()
    }

    fun searchCardShows(search: String){
        mainModel.loadDataWithParameter(search)
    }

    @Synchronized
    fun loadData(shows: MutableList<Shows>?) {
        if (listShows.isNotEmpty())
            listShows.clear()

        if (shows is MutableList<Shows>)
            listShows.addAll(shows)

        if (listShows.isNotEmpty()) {
            mainView.showData(listShows)
            hideKeyboard(mainView)
        } else
            showMessage("Busca não encontrada!")
    }

    fun showMessage(message: String) {
        mainView.showMessage(message)
    }

    fun loadFavorites() : MutableSet<String> = FavoriteShow.getFavorites(mainView)

    fun hideKeyboard(activity: Activity) {
        val view = activity.currentFocus
        view?.let {
            val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}