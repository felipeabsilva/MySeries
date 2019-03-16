package com.felipesilva.myseries

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.d
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule
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
        YourAppGlideModule()
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
        d("123felipe", "${shows[0].show.premiered}")
        d("123felipe", "${shows[0].show.summary}")
        recyclerView.adapter?.notifyDataSetChanged()
    }

    @GlideModule
    inner class YourAppGlideModule : AppGlideModule() {
        override fun applyOptions(context: Context, builder: GlideBuilder) {
            val diskCacheSizeBytes = (1024 * 1024 * 150).toLong() // 100 MB
            builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes))
        }
    }
}
