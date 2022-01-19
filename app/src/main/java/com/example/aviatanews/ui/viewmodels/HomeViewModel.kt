package com.example.aviatanews.ui.viewmodels


import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.aviatanews.Constants.CATEGORY
import com.example.aviatanews.Constants.QUERY
import com.example.aviatanews.data.model.Article
import com.example.aviatanews.data.model.Categories
import com.example.aviatanews.data.repository.NewsRepository
import com.example.aviatanews.data.model.News
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val listCategories = MutableLiveData<List<String>?>()
    private val query = MutableLiveData<Map<String, String>>()
    val news = query.switchMap {
        newsRepository.getNews(it).cachedIn(viewModelScope)
    }

    init {
        /**default category is General
         * but we can change category by function setCategory*/
        val _query = mutableMapOf<String, String>()
        _query[CATEGORY] = Categories.GENERAL.text
        val _list = mutableListOf<String>()
        Categories.values().map {
            _list.add(it.text)
        }
        listCategories.value = _list
        query.value = _query
    }

    suspend fun addArticle(article: Article) = newsRepository.saveArticle(article)

    fun setCategory(category: String) {
        val _query = mutableMapOf<String, String>()
        _query[CATEGORY] = category
        query.value = _query
    }

    fun getCategories() = listCategories.value

    fun search(searchText: String) {
        val _query = query.value as MutableMap<String, String>
        _query[QUERY] = searchText
        query.value = _query
    }


}


