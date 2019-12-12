package de.hshl.isd.tunesearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_track.view.*

class TrackListAdapter : ListAdapter<ItemViewModel, TrackListAdapter.ItemViewHolder>(
    object : DiffUtil.ItemCallback<ItemViewModel>() {
        override fun areItemsTheSame(p0: ItemViewModel, p1: ItemViewModel): Boolean = p0 == p1
        override fun areContentsTheSame(p0: ItemViewModel, p1: ItemViewModel): Boolean =
            p0.content.equals(p1.content)
    }) {

    override fun getItemViewType(position: Int): Int = getItem(position)::class.hashCode()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ItemViewHolder {
        when (viewType) {
            ItemViewModel::class.hashCode() -> return ItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_header, parent, false)
            )
            else -> return TrackViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_track, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.mContentView.text = item.content
        if (holder is TrackViewHolder && item is TrackViewModel) {
            holder.mArtistTextView.text = item.artistName
            Picasso.get().load(item.image)
                .resize(200, 200)
                .centerCrop()
                .into(holder.mArtworkImageView)
        }
    }


    open inner class ItemViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView = mView.content
    }

    inner class TrackViewHolder(mView: View) : ItemViewHolder(mView) {
        val mArtistTextView: TextView = mView.artist
        val mArtworkImageView: ImageView = mView.artwork
    }

}