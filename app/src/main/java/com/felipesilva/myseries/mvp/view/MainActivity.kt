package com.felipesilva.myseries.mvp.view

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.SearchView
import android.util.Log.d
import android.view.Menu
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule
import com.felipesilva.myseries.R
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

    @GlideModule
    inner class YourAppGlideModule : AppGlideModule() {
        override fun applyOptions(context: Context, builder: GlideBuilder) {
            val diskCacheSizeBytes = (1024 * 1024 * 150).toLong() // 100 MB
            builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes))
        }
    }

    override fun showData(shows: MutableList<Shows>) {
        val recyclerView = recycler_view

        if (listShows.isNotEmpty())
            listShows.clear()

        listShows.addAll(shows)
        d("123felipe", "Dia ${shows[0].show.premiered}")
        d("123felipe", "${shows[0].show.summary}")
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu.findItem(R.id.search_button_menu)

        searchItem?.let {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        mainPresenter.searchCardShows(query)
                    }

                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    return false
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }
}
