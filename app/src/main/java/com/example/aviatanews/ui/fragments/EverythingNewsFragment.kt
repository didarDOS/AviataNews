package com.example.aviatanews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aviatanews.R
import com.example.aviatanews.databinding.FragmentEverythingNewsBinding


import com.example.aviatanews.ui.adapters.NewsRecyclerViewAdapter
import com.example.aviatanews.ui.viewmodels.EverythingNewsViewModel
import com.example.aviatanews.ui.viewmodels.HomeViewModel
import com.example.aviatanews.utils.NewsViewModeFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class EverythingNewsFragment : DaggerFragment(R.layout.fragment_everything_news) {

    private var _binding: FragmentEverythingNewsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModeFactory: NewsViewModeFactory

    private val viewModel: EverythingNewsViewModel by viewModels {
        viewModeFactory
    }
    private val homeViewModel: HomeViewModel by viewModels {
        viewModeFactory
    }

    private val newsAdapter by lazy {
        NewsRecyclerViewAdapter {
            lifecycleScope.launch {
                viewModel.addArticle(it)
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEverythingNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.news.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                newsAdapter.submitData(it)
            }
        })

        newsAdapter.addLoadStateListener {
            binding.progressBar.isVisible = it.refresh is LoadState.Loading

        }

        binding.everythingRv.apply {
            adapter = newsAdapter
            this@apply.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            isVisible = true

        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.search(it)
                    binding.emptySearchNews.isVisible = false
                }

                return false
            }

        })
    }

}