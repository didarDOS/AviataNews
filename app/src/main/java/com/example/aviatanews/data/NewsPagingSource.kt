package com.example.aviatanews.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.aviatanews.api.NewsApi
import com.example.aviatanews.data.model.Article
import com.example.aviatanews.data.model.News
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException


class NewsPagingSource(
    private val newsApi: NewsApi,
    private val queryMap: Map<String, String>
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        return try {
            //

            val page = params.key ?: DEFAULT_PAGE_INDEX
            val response = newsApi.loadHeadlines(queryMap, page)
            val news: List<Article> = response.articles


            LoadResult.Page(
                data = news,
                prevKey = null,
                nextKey = page + 1
            )
        } catch (e: IOException) {
            Log.d("IO Error", e.toString())
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.d("Connection Error", e.toString())
            return LoadResult.Error(e)
        }
    }

    @ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val DEFAULT_PAGE_INDEX = 1

    }


}