package com.example.aviatanews.ui.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation

import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.aviatanews.MainActivity
import com.example.aviatanews.R
import com.example.aviatanews.data.model.Article
import com.example.aviatanews.data.model.News
import com.example.aviatanews.databinding.NewsItemBinding
import com.example.aviatanews.ui.fragments.HomeFragment

import com.example.aviatanews.ui.fragments.HomeFragmentDirections
import com.example.aviatanews.ui.fragments.HomeRootFragment
import timber.log.Timber


class NewsRecyclerViewAdapter(private val onBookmark: (Article) -> Unit) :
    PagingDataAdapter<Article, NewsRecyclerViewAdapter.NewsViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val item = getItem(position)
        if (item != null) {
            holder.bind(news = item, listener = createOnClickListener(item))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    private fun createOnClickListener(article: Article): View.OnClickListener{

        return View.OnClickListener {
            val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
            val bundle = bundleOf("article" to article)

            Navigation.findNavController(it).navigate(R.id.detailFragment, bundle)

        }
    }

    inner class NewsViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, news: Article) {
            with(binding){
                /**загружаем фотографии*/
                Glide.with(itemView)
                    .load(news.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.icon_error)
                    .into(newsImageView)
                newsTitle.text = news.title
                bookmark.setOnClickListener {
                    onBookmark(news)
                }
                newsTitle.setOnClickListener(listener)
            }
        }


    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.content == newItem.content

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem
        }
    }


}
