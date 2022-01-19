package com.example.aviatanews.data.db

import androidx.paging.DataSource
import androidx.room.*
import com.example.aviatanews.data.model.Article

@Dao
abstract class ArticlesDao {

    @Query("SELECT * FROM Article")
    abstract fun getArticles(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addArticle(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addAllArticle(list: List<Article>)

    @Query("SELECT * FROM Article")
    abstract fun getPagedNews(): DataSource.Factory<Int, Article>

    @Delete
    abstract fun deleteArticle(article: Article)


}