package com.josemarrima.marvelcomics.listOfComics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.josemarrima.marvelcomics.data.local.Comic
import com.josemarrima.marvelcomics.databinding.ItemLayoutBinding

class ListOfComicsAdapter(private val clickListener: ClickListener) :
    ListAdapter<Comic, RecyclerView.ViewHolder>(ComicDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ComicViewHolder(ItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ComicViewHolder).bind(clickListener, getItem(position))
    }


    class ComicViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: ClickListener,item: Comic) {
            binding.apply {
                clickListener = listener
                comic = item
                executePendingBindings()
            }
        }
    }
}

private class ComicDiffCallback : DiffUtil.ItemCallback<Comic>() {
    override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
        return oldItem == newItem
    }

}

class ClickListener(val clickListener: (comic: Comic) -> Unit) {
    fun onClick(comic: Comic) = clickListener(comic)
}