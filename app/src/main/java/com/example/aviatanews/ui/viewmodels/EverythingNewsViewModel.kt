package com.example.aviatanews.ui.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.aviatanews.Constants
import com.example.aviatanews.data.model.Article
import com.example.aviatanews.data.model.Categories
import com.example.aviatanews.data.model.NameCategories
import com.example.aviatanews.data.model.News
import com.example.aviatanews.data.repository.NewsRepository
import javax.inject.Inject

class EverythingNewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository): ViewModel() {

    private val query = MutableLiveData<Map<String, String>>()
    val news = query.switchMap {
        newsRepository.getEverything(it).cachedIn(viewModelScope)
    }
    init {
        /**default category is General
         * but we can change category by function setCategory*/
        val _query = mutableMapOf<String, String>()
        query.value = _query
    }
    suspend fun addArticle(article: Article) = newsRepository.saveArticle(article)

    fun search(searchText: String) {
        val _query = query.value as MutableMap<String, String>
        _query[Constants.QUERY] = searchText
        query.value = _query
    }
}