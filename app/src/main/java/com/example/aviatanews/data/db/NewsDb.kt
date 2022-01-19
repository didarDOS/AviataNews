package com.example.aviatanews.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aviatanews.data.db.NewsDb.Companion.VERSION
import com.example.aviatanews.data.model.Article


@Database(entities = [Article::class], version = VERSION)
abstract class NewsDb : RoomDatabase(){

    abstract fun articlesDao():ArticlesDao

    companion object {
        const val VERSION = 2
    }
}