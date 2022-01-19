package com.example.aviatanews.di


import com.example.aviatanews.ui.fragments.*
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeEverythingFragment(): EverythingNewsFragment

    @ContributesAndroidInjector
    abstract fun contributeBookmarksFragment(): BookmarksFragment

    @ContributesAndroidInjector
    abstract fun contributeAccountFragment(): AccountFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeRootFragment(): HomeRootFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment
}
