package com.example.aviatanews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.aviatanews.R
import com.example.aviatanews.databinding.FragmentAccountBinding
import com.example.aviatanews.ui.viewmodels.AccountViewModel
import com.example.aviatanews.utils.NewsViewModeFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AccountFragment: DaggerFragment(R.layout.fragment_account) {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModeFactory: NewsViewModeFactory

    private val viewModel: AccountViewModel by viewModels {
        viewModeFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAccountBinding.inflate(inflater, container, false)

        return binding.root
    }

}