package com.example.aviatanews.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aviatanews.ui.viewmodels.AccountViewModel
import com.example.aviatanews.ui.viewmodels.BookmarksFragmentViewModel
import com.example.aviatanews.ui.viewmodels.EverythingNewsViewModel
import com.example.aviatanews.ui.viewmodels.HomeViewModel
import com.example.aviatanews.utils.NewsViewModeFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factoryNews: NewsViewModeFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EverythingNewsViewModel::class)
    abstract fun bindEverythingViewModel(viewModel: EverythingNewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookmarksFragmentViewModel::class)
    abstract fun bindBookMarksFragmentViewModel(viewModel: BookmarksFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(viewModel: AccountViewModel): ViewModel

}
