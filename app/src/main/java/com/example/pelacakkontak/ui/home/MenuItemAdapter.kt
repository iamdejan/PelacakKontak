package com.example.pelacakkontak.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pelacakkontak.databinding.CardMenuBinding

class MenuItemAdapter(menuList: List<Menu>) :
    ListAdapter<Menu, MenuItemAdapter.MenuViewHolder>(DiffCallback()) {

    init {
        submitList(menuList)
    }

    inner class MenuViewHolder(private val binding: CardMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(menu: Menu) {
            binding.apply {
                root.setCardBackgroundColor(menu.backgroundColor)
                imageViewCardImage.setImageResource(menu.resourceId)
                textViewCardMenuTitle.text = menu.title
            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Menu>() {
        override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = CardMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}
