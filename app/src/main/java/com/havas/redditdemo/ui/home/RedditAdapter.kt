package com.havas.redditdemo.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.havas.redditdemo.R
import com.havas.redditdemo.data.model.RedditPost

/**
 * A [ListAdapter] for displaying a list of [RedditPost] objects.
 *
 * @param onPostClick A lambda function to be invoked when a post is clicked.
 */
class RedditAdapter(
    private val onPostClick: (RedditPost) -> Unit
) : ListAdapter<RedditPost, RedditAdapter.RedditViewHolder>(RedditDiffCallback()) {

    /**
     * A [RecyclerView.ViewHolder] for displaying a single [RedditPost].
     *
     * @param itemView The view for the list item.
     */
    inner class RedditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.post_title)
        private val upvotesTextView: TextView = itemView.findViewById(R.id.post_upvotes)
        private val commentsTextView: TextView = itemView.findViewById(R.id.post_comments)
        private val thumbnailImageView: ImageView = itemView.findViewById(R.id.post_image)

        /**
         * Binds the data from a [RedditPost] to the views in the ViewHolder.
         *
         * @param post The [RedditPost] to bind.
         */
        fun bind(post: RedditPost) {
            titleTextView.text = post.title
            upvotesTextView.text = itemView.context.getString(R.string.upvotes_label, post.ups)
            commentsTextView.text = itemView.context.getString(R.string.comments_label, post.numComments)

            if (post.thumbnail != null && post.thumbnail.startsWith("http")) {
                thumbnailImageView.visibility = View.VISIBLE
                thumbnailImageView.load(post.thumbnail) {
                    crossfade(true)
                }
                thumbnailImageView.contentDescription = itemView.context.getString(R.string.post_image_accessibility)
            } else {
                thumbnailImageView.visibility = View.GONE
            }

            itemView.setOnClickListener {
                onPostClick(post)
            }
        }
    }

    /**
     * Creates a new [RedditViewHolder] for a list item.
     *
     * @param parent The parent view.
     * @param viewType The view type of the new View.
     * @return A new [RedditViewHolder] that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditViewHolder {
        return RedditViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.post_item,
                parent,
                false
            )
        )
    }

    /**
     * Binds the data to the [RedditViewHolder] at the specified position.
     *
     * @param holder The [RedditViewHolder] to bind.
     * @param position The position of the item in the list.
     */
    override fun onBindViewHolder(holder: RedditViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

/**
 * A [DiffUtil.ItemCallback] for the [RedditAdapter] to efficiently update the list.
 */
class RedditDiffCallback : DiffUtil.ItemCallback<RedditPost>() {
    /**
     * Checks if two [RedditPost] items are the same.
     *
     * @param oldItem The old item.
     * @param newItem The new item.
     * @return True if the items have the same ID, false otherwise.
     */
    override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * Checks if the contents of two [RedditPost] items are the same.
     *
     * @param oldItem The old item.
     * @param newItem The new item.
     * @return True if the items have the same content, false otherwise.
     */
    override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem == newItem
    }
}
