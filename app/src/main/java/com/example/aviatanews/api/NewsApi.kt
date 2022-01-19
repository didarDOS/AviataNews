package com.example.aviatanews.api

import androidx.viewbinding.BuildConfig
import com.example.aviatanews.data.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NewsApi {

    @GET("v2/everything?apiKey=$API_KEY&language=$EN")
    suspend fun loadEverything(
        @QueryMap request: Map<String, String>,
        @Query("page") nextPageNumber: Int
    ): News

    @GET("v2/top-headlines?apiKey=$API_KEY&language=$EN")
    suspend fun loadHeadlines(
        @QueryMap request: Map<String, String>,
        @Query("page") nextPageNumber: Int
    ): News



    companion object {
        const val API_KEY = "e65ee0938a2a43ebb15923b48faed18d"
//        const val API_KEY = "f23b0d955bfe4cacab6aea2c7496d3ff"
        const val EN = "en"

    }
}

