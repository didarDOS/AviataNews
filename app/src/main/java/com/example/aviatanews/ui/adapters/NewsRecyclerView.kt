package com.example.aviatanews.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aviatanews.data.model.News
import com.example.aviatanews.databinding.NewsItemBinding

class NewsRecyclerView() : PagingDataAdapter<News.Article, NewsRecyclerView.ViewHolder>(DIFF_CALLBACK) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNews: News.Article? = getItem(position)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }


    inner class ViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<News.Article>() {
            override fun areItemsTheSame(oldItem: News.Article, newItem: News.Article): Boolean
                = oldItem.content == newItem.content


            override fun areContentsTheSame(oldItem: News.Article, newItem: News.Article): Boolean
                = oldItem == newItem
        }
    }
}