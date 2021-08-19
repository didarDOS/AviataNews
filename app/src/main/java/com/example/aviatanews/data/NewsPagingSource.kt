package com.example.aviatanews.data

import android.util.Log
import androidx.paging.PagingSource
import com.example.aviatanews.api.NewsApi
import com.example.aviatanews.data.model.News
import retrofit2.HttpException
import java.io.IOException

private const val DEFAULT_PAGE_INDEX = 1

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val queryMap: Map<String, String>?
): PagingSource<Int, News.Article> (){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News.Article> {

        return try {
            //
            val page = params.key ?: DEFAULT_PAGE_INDEX

            val response = newsApi.loadNews(queryMap!!)
            val news = response.articles
            val nextKey = if(news.isEmpty()){
                null
            }else{
                page
            }
            LoadResult.Page(
                data = news,
                prevKey = null,
                nextKey = page
            )
        }catch (e: IOException){
            Log.d("get_error", e.toString())
            return LoadResult.Error(e)
        }catch (e: HttpException){
            Log.d("get_error", e.toString())
            return LoadResult.Error(e)
        }
    }
}