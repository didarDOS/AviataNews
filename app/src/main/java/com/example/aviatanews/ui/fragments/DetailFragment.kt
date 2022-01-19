package com.example.aviatanews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.aviatanews.R
import com.example.aviatanews.data.model.Article
import com.example.aviatanews.data.model.News
import com.example.aviatanews.databinding.FragmentDetailBinding
import dagger.android.support.DaggerFragment


class DetailFragment: DaggerFragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article: Article = arguments?.get("article") as Article
        binding.apply {
            Glide.with(imageView)
                .load(article.urlToImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.icon_error)
                .into(imageView)
            title.text = article.title
            nameUser.text = article.author
            textDescription.text = article.description
            arrowBack.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }

    }


}