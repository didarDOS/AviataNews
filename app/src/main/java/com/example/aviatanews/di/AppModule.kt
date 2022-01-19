package com.example.aviatanews.di

import android.app.Application
import androidx.room.Room
import com.example.aviatanews.data.db.NewsDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        ViewModelModule::class,
    ]
)
object AppModule{
    @Singleton
    @Provides
    fun provideDb(app: Application): NewsDb {
        return Room
            .databaseBuilder(app, NewsDb::class.java, "news.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}