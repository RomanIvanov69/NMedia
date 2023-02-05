package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel
import ru.netology.nmedia.util.AndroidUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onShare(post: Post) {
                viewModel.share(post.id)
            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onEyes(post: Post) {
                viewModel.eyes(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onEdit(post: Post) {
                binding.editGroup.visibility = View.VISIBLE
                viewModel.edit(post)
            }

            override fun onCancelEdit(post: Post) {
                viewModel.notEdit()
            }

        }
        )
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        viewModel.edited.observe(this) { post ->
            if (post.id == 0L)
                return@observe
            with(binding.content) {
                requestFocus()
                setText(post.content)
            }

            binding.save.setOnClickListener {
                with(binding.content) {
                    if (text.isNullOrBlank()) {
                        Toast.makeText(
                            this@MainActivity,
                            context.getString(R.string.empty_content),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        viewModel.editContent(text.toString())
                        viewModel.save()

                        setText("")
                        clearFocus()
                        AndroidUtils.hideKeyboard(this)
                    }
                }
                binding.notEdit.setOnClickListener {
                    with(binding.content) {
                        if (text.isNotEmpty()) {
                            Toast.makeText(
                                this@MainActivity,
                                context.getString(R.string.notEdit),
                                Toast.LENGTH_SHORT
                            ).show()

                            viewModel.notEdit()
                            text.clear()
                            clearFocus()
                            binding.editGroup.visibility = View.GONE
                            AndroidUtils.hideKeyboard(this)
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                context.getString(R.string.empty_content),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}

