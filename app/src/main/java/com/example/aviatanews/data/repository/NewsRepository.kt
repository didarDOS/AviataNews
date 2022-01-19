package com.example.aviatanews.data.repository

import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.aviatanews.api.NewsApi
import com.example.aviatanews.data.NewsEverythingPagingSource
import com.example.aviatanews.data.NewsPagingSource
import com.example.aviatanews.data.db.NewsDb
import com.example.aviatanews.data.model.Article
import com.example.aviatanews.data.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDb: NewsDb

    ) {
    /***/
    fun getNews(queryMap: Map<String, String>) =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(newsApi, queryMap) }
        ).flow.asLiveData()

    /***/
    fun getEverything(queryMap: Map<String, String>) =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsEverythingPagingSource(queryMap, newsApi) }
        ).flow.asLiveData()

    suspend fun saveArticle(article: Article) = withContext(Dispatchers.IO){
        newsDb.articlesDao().addArticle(article)
    }
    suspend fun deleteArticle(article: Article) = withContext(Dispatchers.IO){
        newsDb.articlesDao().deleteArticle(article)
    }

    suspend fun getAllBookMark() = withContext(Dispatchers.IO){
        newsDb.articlesDao().getArticles()
    }

}