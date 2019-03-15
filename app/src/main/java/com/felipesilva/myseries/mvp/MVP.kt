package com.felipesilva.myseries.mvp

import com.felipesilva.myseries.data.Shows

interface MVP {
    interface MainViewImpl {
        fun showData(shows: MutableList<Shows>)
    }
}