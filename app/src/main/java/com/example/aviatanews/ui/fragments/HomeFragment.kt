package com.example.aviatanews.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aviatanews.R
import com.example.aviatanews.data.model.Categories
import com.example.aviatanews.databinding.FragmentHomeBinding
import com.example.aviatanews.ui.adapters.NewsRecyclerViewAdapter
import com.example.aviatanews.ui.adapters.RecyclerItemClickListener
import com.example.aviatanews.ui.adapters.CategoriesViewPagerAdapter
import com.example.aviatanews.ui.viewmodels.HomeViewModel
import com.example.aviatanews.utils.NewsViewModeFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class HomeFragment : DaggerFragment(R.layout.fragment_home) {

    @Inject
    lateinit var viewModeFactory: NewsViewModeFactory

    private val viewModel: HomeViewModel by viewModels {
        viewModeFactory
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoriesVp.setPadding(20, 0, 10, 0)

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        layoutManager.initialPrefetchItemCount = 4
        binding.categoriesVp.layoutManager = layoutManager
        val adapterCategories =
            CategoriesViewPagerAdapter(viewModel.getCategories() as ArrayList<String>)
        binding.categoriesVp.adapter = adapterCategories

        binding.categoriesVp.addOnItemTouchListener(
            RecyclerItemClickListener(
                requireContext(),
                binding.categoriesVp,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        viewModel.setCategory(Categories.values()[position].text)

                    }

                    override fun onItemLongClick(view: View?, position: Int) {

                    }

                })
        )




        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.search(it) }
                return false
            }

        })

        viewModel.news.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                newsAdapter.submitData(it)
            }
        })

        newsAdapter.addLoadStateListener {
            binding.progressBar.isVisible = it.refresh is LoadState.Loading
        }

        binding.newsRecyclerView.apply {
            adapter = newsAdapter
            this@apply.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            isVisible = true
        }

    }

}