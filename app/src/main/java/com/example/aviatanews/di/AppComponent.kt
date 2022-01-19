package com.example.aviatanews.di

/**
 * @author Didar Ospanov
 * */

import android.app.Application
import com.example.aviatanews.MainActivity
import com.example.aviatanews.NewsApplication
import com.example.aviatanews.utils.NewsViewModeFactory
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 *
 * @author Didar Ospanov
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        MainActivityModule::class
    ]
)
interface AppComponent: AndroidInjector<NewsApplication> {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    override fun inject(instance: NewsApplication?)

}