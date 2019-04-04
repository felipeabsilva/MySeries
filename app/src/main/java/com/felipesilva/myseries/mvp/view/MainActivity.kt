package com.felipesilva.myseries.mvp.view

import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.felipesilva.myseries.R
import com.felipesilva.myseries.adapter.ShowCardAdapter
import com.felipesilva.myseries.mvp.MVP
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), MVP.MainViewImpl, KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val mainPresenter : MVP.MainPresenterImpl by instance()
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeUI()
        //YourAppGlideModule()
    }

    private fun initializeUI() {
        recycler_view.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)

            mainPresenter.getSeriesList().observe(this@MainActivity, Observer {
                adapter = ShowCardAdapter(mainPresenter.getSeriesList())
            })
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu.findItem(R.id.search_button_menu)

         searchItem.let {
            searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        mainPresenter.makeCallSeriesList(query)
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

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /*
    @GlideModule
    inner class YourAppGlideModule : AppGlideModule() {
        override fun applyOptions(context: Context, builder: GlideBuilder) {
            val diskCacheSizeBytes = (1024 * 1024 * 150).toLong() // 100 MB
            builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes))
        }
    }
    */

    override fun setRecyclerAndProgressViewVisibility(recyclerVisibility: Int, progressVisibility: Int) {
        recycler_view.visibility = recyclerVisibility
        progress_bar.visibility = progressVisibility
    }
}
