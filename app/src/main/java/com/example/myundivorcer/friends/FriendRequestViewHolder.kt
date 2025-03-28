package com.example.myundivorcer.friends

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.myundivorcer.R
import com.example.myundivorcer.dataClasses.FriendRequest
import com.example.myundivorcer.utils.Utils.byteArrayToBitmap

class FriendRequestViewHolder(
    itemView: View,
    private val acceptCallback: (FriendRequest) -> Unit,
    private val ignoreCallback: (FriendRequest) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val usernameTextView: TextView = itemView.findViewById(R.id.usernameTextView)
    private val userImageView: ImageView = itemView.findViewById(R.id.friendImageView)

    fun bind(friendRequest: FriendRequest) {
        usernameTextView.text = friendRequest.username
        val bitmap = byteArrayToBitmap(friendRequest.imageByteArray)
        userImageView.setImageBitmap(bitmap)

        itemView.setOnClickListener {
            showFriendRequestDialog(itemView.context, friendRequest)
        }
    }

    private fun showFriendRequestDialog(context: Context, friendRequest: FriendRequest) {
        AlertDialog.Builder(context)
            .setTitle("בקשת חברות")
            .setMessage("האם תרצה לאשר את בקשת החברות")
            .setPositiveButton("אשר") { dialog, _ ->
                acceptCallback.invoke(friendRequest)
                dialog.dismiss()
            }
            .setNegativeButton("דחה") { dialog, _ ->
                ignoreCallback.invoke(friendRequest)
                dialog.dismiss()
            }
            .show()
    }
}
