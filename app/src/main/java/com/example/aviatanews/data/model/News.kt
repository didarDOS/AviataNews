package com.example.aviatanews.data.model


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
) : Parcelable {
    @Parcelize
    data class Article(
        val author: String,
        val content: String,
        val description: String,
        val publishedAt: String,
        val source: Source,
        val title: String,
        val url: String,
        val urlToImage: String
    ) : Parcelable {
        @Parcelize
        data class Source(
            val id: Int?,
            val name: String
        ) : Parcelable
    }
}