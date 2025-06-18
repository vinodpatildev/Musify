package com.vinodpatildev.musify.adapters

import androidx.recyclerview.widget.AsyncListDiffer
import com.vinodpatildev.musify.databinding.SwipeItemBinding
import com.vinodpatildev.musify.R

class SwipeSongAdapter : BaseSongAdapter(R.layout.swipe_item) {

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        (holder.binding as SwipeItemBinding).apply {
            val text = "${song.title} - ${song.subtitle}"
            tvPrimary.text = text

            root.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(song)
                }
            }
        }
    }
}