package ru.netology.nmedia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.nmedia.R
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

        binding.editContent.setOnClickListener {
            binding.group.visibility = View.VISIBLE
        }

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onShare(post: Post) {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
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
            with(binding.editContent) {
                requestFocus()
                setText(post.content)
            }

            binding.saveButton.setOnClickListener {
                with(binding.editContent) {
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
                        binding.group.visibility = View.GONE
                    }
                }
                binding.notEdit.setOnClickListener {
                    binding.group.visibility = View.VISIBLE
                    with(binding.editContent) {
                        if (text.isNotEmpty()) {
                            Toast.makeText(
                                this@MainActivity,
                                context.getString(R.string.notEdit),
                                Toast.LENGTH_SHORT
                            ).show()

                            viewModel.notEdit()
                            text.clear()
                            clearFocus()
                            AndroidUtils.hideKeyboard(this)
                            binding.group.visibility = View.GONE
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

