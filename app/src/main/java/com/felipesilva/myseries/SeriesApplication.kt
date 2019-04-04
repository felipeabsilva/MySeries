package com.felipesilva.myseries

import android.app.Application
import com.felipesilva.myseries.data.db.TvMazeDatabase
import com.felipesilva.myseries.data.db.TvMazeDatabaseImpl
import com.felipesilva.myseries.data.remote.retrofit.RetrofitConfig
import com.felipesilva.myseries.mvp.MVP
import com.felipesilva.myseries.mvp.model.MainModel
import com.felipesilva.myseries.mvp.presenter.MainPresenter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class SeriesApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        bind() from provider { RetrofitConfig() }

        bind<TvMazeDatabase>() with singleton { TvMazeDatabaseImpl(instance()) }

        bind<MVP.MainModelImpl>() with singleton { MainModel(instance()) }

        bind<MVP.MainPresenterImpl>() with singleton { MainPresenter(instance()) }
    }
}