package com.vinithius.wtest.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

private val listModules by lazy {
    listOf(
        repositoryModule,
        repositoryDataModule,
        viewModelModule,
        networkModule,
        daoModule
    )
}

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listModules)
        }
    }
}
