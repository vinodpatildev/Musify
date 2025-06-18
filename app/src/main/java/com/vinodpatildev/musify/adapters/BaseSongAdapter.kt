package com.vinodpatildev.musify.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.vinodpatildev.musify.R
import com.vinodpatildev.musify.data.entities.Song
import com.vinodpatildev.musify.databinding.ListItemBinding
import com.vinodpatildev.musify.databinding.SwipeItemBinding

abstract class BaseSongAdapter(
    private val layoutId: Int
) : RecyclerView.Adapter<BaseSongAdapter.SongViewHolder>() {

    class SongViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    protected val diffCallback = object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.mediaId == newItem.mediaId
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    protected abstract val differ: AsyncListDiffer<Song>

    var songs: List<Song>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when (layoutId) {
            R.layout.list_item -> ListItemBinding.inflate(inflater, parent, false)
            R.layout.swipe_item -> SwipeItemBinding.inflate(inflater, parent, false)
            else -> throw IllegalArgumentException("Unknown layout ID: $layoutId")
        }
        return SongViewHolder(binding)
    }

    protected var onItemClickListener: ((Song) -> Unit)? = null

    fun setItemClickListener(listener: (Song) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}