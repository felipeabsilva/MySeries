package com.felipesilva.myseries

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.felipesilva.myseries.adapter.ShowCardAdapter
import com.felipesilva.myseries.data.Shows
import com.felipesilva.myseries.mvp.MVP
import com.felipesilva.myseries.mvp.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MVP.MainViewImpl {
    private lateinit var mainPresenter : MainPresenter
    private val listShows = mutableListOf<Shows>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ShowCardAdapter(listShows)
        }

        initializeInstances()
        mainPresenter.listCardsShows()
    }

    private fun initializeInstances() {
        MainPresenter.initializeInstance()
        mainPresenter = MainPresenter.getInstance()
        mainPresenter.setActivity(this)
        mainPresenter.initializeModelInstance()
    }

    override fun showData(shows: MutableList<Shows>) {
        val recyclerView = recycler_view

        if (listShows.isNotEmpty())
            listShows.clear()

        listShows.addAll(shows)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}
