package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostViewModel
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->
            with(binding)
            {
                author.text = post.author
                published.text = post.published
                content.text = post.content

                heart.setImageResource(
                    if (post.likedByMe) R.drawable.red_heart else R.drawable.baseline_favorite_border_24
                )
                heartTextView.text = clickCount(post.likeCount)
                eyesTextView.text = clickCount(post.lookCount)
                shareTextView.text = clickCount(post.shareCount)
            }
        }
        binding.heart.setOnClickListener {
            viewModel.heart()
        }

        binding.share.setOnClickListener {
            viewModel.share()
        }

        binding.eyes.setOnClickListener {
            viewModel.eyes()
        }
        val textView: TextView = findViewById(R.id.content)
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}

