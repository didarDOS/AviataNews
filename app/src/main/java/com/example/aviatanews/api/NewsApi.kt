package com.example.aviatanews.api

import androidx.viewbinding.BuildConfig
import com.example.aviatanews.data.model.News
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NewsApi {

    @GET("v2/everything?apiKey=$API_KEY")
    fun loadNews(
        @QueryMap request: Map<String, String>
    ): News

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun loadHeadlines(
        @QueryMap request: Map<String, String>
    ): News



    companion object {

        const val API_KEY = "e65ee0938a2a43ebb15923b48faed18d"
    }
}

