package com.example.aviatanews

import android.app.Application
import com.example.aviatanews.di.DaggerAppComponent

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NewsApplication: DaggerApplication() {
    private val appComponent = DaggerAppComponent.builder().application(this).build()
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent.inject(this)
        return appComponent
    }
}