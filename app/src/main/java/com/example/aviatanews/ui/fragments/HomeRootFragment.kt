package com.example.aviatanews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.aviatanews.R
import com.example.aviatanews.databinding.FragmentHomeBinding
import com.example.aviatanews.databinding.FragmentHomeRootBinding
import dagger.android.support.DaggerFragment

class HomeRootFragment: DaggerFragment(R.layout.fragment_home_root) {

    private var _binding: FragmentHomeRootBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeRootBinding.inflate(inflater, container, false)

        return binding.root
    }


}