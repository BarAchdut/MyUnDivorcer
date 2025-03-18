package com.example.myundivorcer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myundivorcer.activities.BaseActivity
import com.example.myundivorcer.R
import com.google.android.material.imageview.ShapeableImageView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as BaseActivity?)?.updateTitle("בית")
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val bundle = Bundle()
        bundle.putString("USERNAME_KEY", (activity as BaseActivity?)?.username)

        val btnGoToMyRecipes = view.findViewById<ShapeableImageView>(R.id.btnGoToMyRecipes)
        btnGoToMyRecipes.setOnClickListener {
            // Using Safe Args navigation
            findNavController().navigate(
                R.id.action_homeFragment_to_recipesFragment,
                bundle
            )
        }

        val btnGoToMyWishlists = view.findViewById<ShapeableImageView>(R.id.btnGoToMyWishlists1)
        btnGoToMyWishlists.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_wishlistsFragment,
                bundle
            )
            (activity as BaseActivity?)?.updateNavigationBarToWishlists()
        }

        return view
    }
}