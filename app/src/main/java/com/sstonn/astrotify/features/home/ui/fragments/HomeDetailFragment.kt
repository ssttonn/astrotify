package com.sstonn.astrotify.features.home.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.fragment.findNavController
import com.sstonn.astrotify.R
import com.sstonn.astrotify.databinding.FragmentHomeDetailBinding

class HomeDetailFragment : Fragment() {
    private lateinit var binding: FragmentHomeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeDetailBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findNavController().setOnBackPressedDispatcher(dispatcher = OnBackPressedDispatcher{

        })
    }
}