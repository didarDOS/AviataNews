package com.example.aviatanews.di


import com.example.aviatanews.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope
import javax.inject.Singleton


@Suppress("unused")
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(
        modules = [FragmentBuildersModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}

