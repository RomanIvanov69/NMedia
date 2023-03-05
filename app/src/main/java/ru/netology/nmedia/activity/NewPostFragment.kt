package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.util.StringArg
import ru.netology.nmedia.viewmodel.PostViewModel

class NewPostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNewPostBinding.inflate(layoutInflater, container, false)

        val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)
        arguments?.textArg?.let(binding.editContent::setText)

        val text = activity?.intent?.getStringExtra(Intent.EXTRA_TEXT)
        binding.editContent.setText(text)

        binding.buttonOk.setOnClickListener {
            val text = binding.editContent.text.toString()
            if (text.isNotBlank()) {
                viewModel.editContent(text)
                viewModel.save()
            } else {
                Toast.makeText(
                    this.context,
                    R.string.empty_content,
                    Toast.LENGTH_SHORT
                ).show()
            }
                findNavController().navigateUp()
            }
        return binding.root
    }
    companion object {
        var Bundle.textArg: String? by StringArg
    }
}



