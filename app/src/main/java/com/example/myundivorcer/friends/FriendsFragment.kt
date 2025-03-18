package com.example.myundivorcer.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myundivorcer.activities.BaseActivity
import com.example.myundivorcer.R
import com.google.android.material.imageview.ShapeableImageView

class FriendsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (activity as BaseActivity?)?.updateTitle("חברים")
        val view = inflater.inflate(R.layout.fragment_friends, container, false)

        val friendRequestsButton: ShapeableImageView =
            view.findViewById(R.id.requestFriendImgButton)
        val searchFriendButton: ShapeableImageView =
            view.findViewById(R.id.searchFriendImgButton)
        val myFriendsButton: ShapeableImageView =
            view.findViewById(R.id.myFriendsImgButton)

        searchFriendButton.setOnClickListener {
            findNavController().navigate(R.id.action_friendsFragment_to_friendSearchFragment)
        }

        friendRequestsButton.setOnClickListener {
            findNavController().navigate(R.id.action_friendsFragment_to_friendRequestsFragment)
        }

        myFriendsButton.setOnClickListener {
            findNavController().navigate(R.id.action_friendsFragment_to_showFriendsFragment)
        }

        return view
    }
}