package com.example.aviatanews.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aviatanews.data.NewsRepository
import com.example.aviatanews.data.model.News
import com.example.aviatanews.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    private var _news = MutableLiveData<Resource<News.Article>>()
    val news: LiveData<Resource<News.Article>>
        get() = _news

    fun loadNews(){
        newsRepository.getNews()
    }

}