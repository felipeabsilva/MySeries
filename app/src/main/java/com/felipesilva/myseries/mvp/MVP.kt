package com.felipesilva.myseries.mvp

import android.app.Activity
import androidx.lifecycle.LiveData
import com.felipesilva.myseries.data.model.Show
import com.felipesilva.myseries.data.model.Shows
import com.felipesilva.myseries.mvp.view.MainActivity

interface MVP {
    interface MainViewImpl {
        fun showMessage(message: String)
        fun setRecyclerAndProgressViewVisibility(recyclerVisibility: Int, progressVisibility: Int)
    }

    interface MainPresenterImpl {
        fun getSeriesList() : LiveData<List<Shows>>
        fun makeCallSeriesList(name: String)
    }

    interface MainModelImpl {
        fun getSeriesList() : LiveData<List<Shows>>
        fun makeCallSeriesList(name: String)
    }
}