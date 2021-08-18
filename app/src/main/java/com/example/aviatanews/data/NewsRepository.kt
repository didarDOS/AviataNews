package com.example.aviatanews.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.aviatanews.api.NewsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsApi: NewsApi) {
    fun  getNews() =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            )
        ).liveData
}