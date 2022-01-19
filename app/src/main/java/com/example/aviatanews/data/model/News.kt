package com.example.aviatanews.data.model



import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.aviatanews.utils.extensions.fromJson
import com.example.aviatanews.utils.extensions.gson
import kotlinx.android.parcel.Parcelize

@Parcelize

data class News(

    val articles: List<Article>,
    val status: String,
    val totalResults: Int
) : Parcelable {

}

@Parcelize
@Entity
@TypeConverters(value = [OrderTypeConverters::class])
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source,
    val title: String?,
    @PrimaryKey
    val url: String,
    val urlToImage: String?
) : Parcelable {
    @Parcelize
    data class Source(
        val id: String?,
        val name: String?
    ) : Parcelable
}

object OrderTypeConverters{
    @TypeConverter
    @JvmStatic
    fun rolesListToString(articles: List<Article>): String? = articles.let { gson.toJson(it) }
    @TypeConverter
    @JvmStatic
    fun stringToList(string: String): List<Article>? = string.let { gson.fromJson(it) }

    @TypeConverter
    @JvmStatic
    fun rolesSourceToString(source: Article.Source): String? = source.let { gson.toJson(it) }
    @TypeConverter
    @JvmStatic
    fun stringToSource(string: String): Article.Source? = string.let { gson.fromJson(it) }
}