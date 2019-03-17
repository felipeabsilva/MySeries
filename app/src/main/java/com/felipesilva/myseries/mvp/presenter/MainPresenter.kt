package com.felipesilva.myseries.mvp.presenter

import android.util.Log.d
import com.felipesilva.myseries.mvp.view.MainActivity
import com.felipesilva.myseries.data.Shows
import com.felipesilva.myseries.mvp.model.MainModel
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream

class MainPresenter private constructor() {
    private lateinit var mainModel : MainModel
    private val listShows = mutableListOf<Shows>()
    private lateinit var mainView : MainActivity
    private val listFavorite = mutableSetOf<String>()

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
            mainView.hideKeyboard()
        } else
            showMessage("Busca não encontrada!")
    }

    fun showMessage(message: String) {
        mainView.showMessage(message)
    }

    fun loadFavorites() : MutableSet<String> {
        val FILE_NAME = "favorite_list"
        val file : File = mainView.getFileStreamPath(FILE_NAME)

        if (file.exists()) {
            val fis = FileInputStream(file)
            val ois = ObjectInputStream(fis)

            val retorno = ois.readObject() as MutableSet<String>

            if(retorno is MutableSet<String>)
                if (listFavorite.isNotEmpty()) {
                    listFavorite.clear()
                    listFavorite.addAll(retorno)
                } else
                    listFavorite.addAll(retorno)

            fis.close()
            ois.close()
        }

        return listFavorite
    }
}