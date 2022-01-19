package com.example.aviatanews.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aviatanews.data.model.Article
import com.example.aviatanews.data.repository.NewsRepository
import javax.inject.Inject

class BookmarksFragmentViewModel @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {
    val newsBookmarks = MutableLiveData<List<Article>>()
    suspend fun getAllBookmarks() {
        newsBookmarks.value = newsRepository.getAllBookMark()
    }
    suspend fun saveBookmark(article: Article) {
        newsRepository.saveArticle(article)
    }
    suspend fun deleteBookmark(article: Article) {
        newsRepository.deleteArticle(article)
    }



}