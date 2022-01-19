package com.example.aviatanews.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.aviatanews.R
import com.example.aviatanews.data.model.Article
import com.example.aviatanews.databinding.LayoutNewsBookmarksBinding


class BookmarksRecyclerViewAdapter(private val news: List<Article>) : RecyclerView.Adapter<BookmarksRecyclerViewAdapter.BookmarksRecyclerViewHolder>(){

    inner class BookmarksRecyclerViewHolder(private val binding: LayoutNewsBookmarksBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(news: Article) {
            with(binding){
                /**загружаем фотографии*/
                Glide.with(cardViewImage)
                    .load(news.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.icon_error)
                    .into(cardViewImage)
                cardViewTextTitle.text = news.title
                cardViewTextSource.text = news.source.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarksRecyclerViewHolder {
        val binding = LayoutNewsBookmarksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarksRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarksRecyclerViewHolder, position: Int) {
        val item = news.get(position)
        if (item != null) {
            holder.bind(news = item,)
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }
}