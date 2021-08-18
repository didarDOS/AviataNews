package com.example.aviatanews.data

import androidx.paging.PagingSource
import com.example.aviatanews.api.NewsApi
import com.example.aviatanews.data.model.News
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val queryMap: Map<String, String>
): PagingSource<Int, News.Article> (){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News.Article> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = newsApi.loadNews(queryMap)
            val news = response.articles

            LoadResult.Page(
                data = news,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position-1,
                nextKey = if (news.isEmpty()) null else position+1
            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}