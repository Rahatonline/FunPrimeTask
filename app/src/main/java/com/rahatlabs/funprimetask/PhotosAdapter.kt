package com.rahatlabs.funprimetask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahatlabs.funprimetask.model.Photo

class PhotosAdapter(private val onClick: (Photo) -> Unit) : ListAdapter<Photo, PhotosAdapter.PhotoViewHolder>(DiffCallback()) {

    class PhotoViewHolder(view: View, val onClick: (Photo) -> Unit) : RecyclerView.ViewHolder(view) {
        private val titleView: TextView = view.findViewById(R.id.title)
        private val imageView: ImageView = view.findViewById(R.id.thumbnail)
        private var currentPhoto: Photo? = null

        init {
            view.setOnClickListener {
                currentPhoto?.let {
                    onClick(it)
                }
            }
        }

        fun bind(photo: Photo) {
            currentPhoto = photo

            titleView.text = photo.title
            Glide.with(itemView.context)
                .load(photo.thumbnailUrl)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }
}
