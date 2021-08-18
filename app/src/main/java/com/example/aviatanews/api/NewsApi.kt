package com.example.aviatanews.api

import androidx.viewbinding.BuildConfig
import com.example.aviatanews.BuildConfig.NEWS_API_KEY
import com.example.aviatanews.data.model.News
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NewsApi {

    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY = "e65ee0938a2a43ebb15923b48faed18d"
    }

    @GET("v2/everything?apiKey=$API_KEY")
    fun loadNews(
        @QueryMap request: Map<String, String>
    ): News

}