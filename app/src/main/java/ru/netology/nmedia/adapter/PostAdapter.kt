package ru.netology.nmedia.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit

class PostAdapter (
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener) :
                    ListAdapter<Post, PostViewHolder>(Post)

}

class PostViewHolder {
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: onShareListener): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(post: Post) {
            binding.apply {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                heart.setImageResource(
                    if (post.likedByMe) R.drawable.baseline_favorite_border_24 else R.drawable.red_heart
                )
                heart.setOnClickListener {
                    onLikeListener(post)
                }
                share.setOnClickListener {
                    onShareListener(post)
                }
            }
        }
    }


}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}
