package com.example.newscompose

import android.app.Application
import android.content.Context
import com.example.newscompose.di.AppComponent
import com.example.newscompose.di.DaggerAppComponent

class NewsApplication: Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is NewsApplication -> appComponent
        else -> applicationContext.appComponent
    }