package com.example.myundivorcer.wishLists

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myundivorcer.R
import com.example.myundivorcer.dataClasses.ShopListItem
import com.example.myundivorcer.utils.StrikeThroughTextView
import java.io.File

class ShopListAdapter(
    val items: MutableList<ShopListItem>,
    var itemLongClickListener: OnItemLongClickListener? = null
) : RecyclerView.Adapter<ShopListAdapter.ShopListHolder>() {

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int, view: View)
    }

    class ShopListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListHolder {
        return ShopListHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.shop_list_item,
                parent,
                false
            )
        )
    }

    fun initialList(itemsToAdd: List<ShopListItem>) {
        items.addAll(itemsToAdd)
    }

    fun addShopListItem(item: ShopListItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    private fun toggleStrikeThrough(
        tvShopListItem: StrikeThroughTextView,
        checked: Boolean,
        pencilImageView: ImageView
    ) {
        if (checked) {
            animateCrayonMark(pencilImageView, tvShopListItem)
            tvShopListItem.setStrikeThrough(true)
        } else {
            tvShopListItem.setStrikeThrough(false)
            tvShopListItem.setStrikeThroughTextFlag(false)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ShopListHolder, position: Int) {
        val curItem = items[position]
        holder.itemView.apply {
            val tvShopListItem: StrikeThroughTextView = findViewById(R.id.tvShopItemTitle)
            val cbCheckBox: CheckBox = findViewById(R.id.cbBought)
            val countItem: TextView = findViewById(R.id.tvItemCount)
            val pencilImageView: ImageView = findViewById(R.id.pencilImageView)
            val ivItemImage: ImageView = findViewById(R.id.ivItemImage)

            tvShopListItem.text = curItem.title
            cbCheckBox.isChecked = curItem.checked
            countItem.text = "${curItem.count} ${curItem.unit}"
            tvShopListItem.setStrikeThroughTextFlag(cbCheckBox.isChecked)

            // Load image from internal file path
            curItem.imageUri?.let { path ->
                val file = File(path)
                if (file.exists()) {
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    ivItemImage.setImageBitmap(bitmap)
                    ivItemImage.visibility = View.VISIBLE
                } else {
                    Log.w("ShopListAdapter", "Image file not found: $path")
                    ivItemImage.setImageDrawable(null)
                    ivItemImage.visibility = View.GONE
                }
            } ?: run {
                ivItemImage.setImageDrawable(null)
                ivItemImage.visibility = View.GONE
            }

            cbCheckBox.setOnCheckedChangeListener { _, checked ->
                toggleStrikeThrough(tvShopListItem, checked, pencilImageView)
                curItem.checked = checked
            }
        }

        holder.itemView.setOnLongClickListener {
            itemLongClickListener?.onItemLongClick(position, holder.itemView)
            true
        }
    }

    private fun animateCrayonMark(pencilImageView: ImageView, textView: StrikeThroughTextView) {
        pencilImageView.visibility = View.VISIBLE
        val tvStartX = textView.x
        val tvEndX = tvStartX + textView.width
        pencilImageView.translationX = tvStartX
        pencilImageView.setImageResource(R.drawable.pencil)

        val movePencil = ObjectAnimator.ofFloat(pencilImageView, "translationX", tvStartX, tvEndX)
        movePencil.duration = 500

        movePencil.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            textView.setStrikeThrough(true, value - tvStartX)
        }

        movePencil.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                pencilImageView.visibility = View.GONE
            }
        })
        movePencil.start()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}