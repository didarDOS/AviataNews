package com.example.aviatanews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aviatanews.R
import com.example.aviatanews.databinding.FragmentBookmarksBinding
import com.example.aviatanews.ui.adapters.BookmarksRecyclerViewAdapter
import com.example.aviatanews.ui.viewmodels.BookmarksFragmentViewModel
import com.example.aviatanews.utils.NewsViewModeFactory
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookmarksFragment : DaggerFragment(R.layout.fragment_bookmarks) {
    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModeFactory: NewsViewModeFactory

    private val viewModel: BookmarksFragmentViewModel by viewModels {
        viewModeFactory
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bookmarksRv.apply {
            this@apply.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)

        }

        viewModel.newsBookmarks.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                val newsBookmarksAdapter =
                    BookmarksRecyclerViewAdapter(it)
                binding.bookmarksRv.apply {
                    adapter = newsBookmarksAdapter
                    isVisible = true
                }
                binding.emptyBookmarksMsg.isVisible = false
            }else{
                binding.emptyBookmarksMsg.isVisible = true
            }


        })

        lifecycleScope.launch {
            viewModel.getAllBookmarks()
        }

    }
}