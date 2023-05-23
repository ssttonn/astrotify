package com.sstonn.astrotify.common.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.sstonn.astrotify.R
import com.sstonn.astrotify.databinding.FragmentMainNavigationBinding

class MainNavigationFragment : Fragment() {
    private lateinit var binding: FragmentMainNavigationBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainNavigationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.mainNavFragment) as NavHostFragment

        navController = navHostFragment.navController
        binding.mainBottomNavigationView.setupWithNavController(navController)
        binding.mainBottomNavigationView.setOnItemSelectedListener {
            Log.d("MainNavigationFragment", "onViewCreated:${it.title}")
            it.onNavDestinationSelected(navController)
            true
        }
        Log.d("MainNavigationFragment", "onViewCreated:${navController.graph.startDestDisplayName}")
    }
}